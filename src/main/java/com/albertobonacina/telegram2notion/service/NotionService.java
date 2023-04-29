package com.albertobonacina.telegram2notion.service;

import com.albertobonacina.telegram2notion.client.NotionPageResource;
import com.albertobonacina.telegram2notion.dto.NotionResultDto;
import com.albertobonacina.telegram2notion.model.*;
import com.albertobonacina.telegram2notion.utils.UrlUtils;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ApplicationScoped
public class NotionService implements INotionService {

    @ConfigProperty(name = "notion.db")
    String notionDB;
    @ConfigProperty(name = "telegram.bot.formatted.response")
    String botFormattedResponse;

    @Inject
    NotionPageResource notionPageResource;

    @Override
    public NotionResultDto send(String telegramMessage) {

        //Get urls inside message
        String myUrl = UrlUtils.extractUrl(telegramMessage);
        List<String> hashtagList = UrlUtils.extractHashTags(telegramMessage);
        String customTitle = UrlUtils.extractTitleFromMessage(telegramMessage);

        String titleFromMyUrl = "";
        if(customTitle != null){
           titleFromMyUrl = customTitle;
        } else {
            try {
                titleFromMyUrl = UrlUtils.getTitleFromWebPage(myUrl);
            } catch (IOException e) {
                //TODO: return custom message to the bot
                log.error(e.getMessage());
            }
        }

        NotionPage newNotionPage = new NotionPage(
            new NotionPageParent(notionDB),
            new NotionPageProperties(
                new NotionPropertiesUrl(myUrl),
                new NotionPropertiesDate(
                    new PropertiesDateDate(
                        ZonedDateTime.now(ZoneId.of("Europe/Rome"))
                                .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME), null
                    )
                ),
                new NotionPropertiesTag(
                    new ArrayList<>(
                        hashtagList.stream()
                                .map(PropertiesMultiSelect::new)
                                .collect(Collectors.toList())
                    )
                ),
                new NotionPropertiesName(
                    new ArrayList<>(
                        Collections.singleton(
                            new PropertiesTitle(
                                new PropertiesTitleText(titleFromMyUrl)
                            )
                        )
                    )
                )
            )
        );

        notionPageResource.createNewPage(newNotionPage);

        return new NotionResultDto(true,
            String.format(botFormattedResponse, titleFromMyUrl, String.join(", ",hashtagList))
        );
    }
}