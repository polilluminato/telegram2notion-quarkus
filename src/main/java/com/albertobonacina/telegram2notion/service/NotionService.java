package com.albertobonacina.telegram2notion.service;

import com.albertobonacina.telegram2notion.client.NotionPageResource;
import com.albertobonacina.telegram2notion.model.*;
import com.albertobonacina.telegram2notion.utils.UrlUtils;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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

    @Inject
    NotionPageResource notionPageResource;

    @Override
    public Boolean send(String telegramMessage) {

        //Get urls inside message
        List<String> urlList = UrlUtils.extractUrls(telegramMessage);
        List<String> hashtagList = UrlUtils.extractHashTags(telegramMessage);

        //Get the first url in the list because I have to get the title of the webpage
        String myUrl = urlList.get(0);
        String titleFromMyUrl = "";
        try {
            titleFromMyUrl = UrlUtils.getTitleFromWebPage(myUrl);
        } catch (IOException e) {
            //TODO: return custom message to the bot
            log.error(e.getMessage());
        }

        NotionPage newNotionPage = new NotionPage();
            newNotionPage.setParent(new NotionPageParent(notionDB));
                NotionPageProperties notionPageProperties = new NotionPageProperties();
                    notionPageProperties.setUrl(
                        new NotionPropertiesUrl(myUrl)
                    );
                    notionPageProperties.setDate(
                        new NotionPropertiesDate(
                            new PropertiesDateDate(
                                ZonedDateTime.now(ZoneId.of("Europe/Rome"))
                                    .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME), null)
                        )
                    );
                    notionPageProperties.setTag(
                        new NotionPropertiesTag(
                            new ArrayList<>(
                                hashtagList.stream()
                                    .map(PropertiesMultiSelect::new)
                                    .collect(Collectors.toList()))
                            )
                    );
                    notionPageProperties.setName(
                        new NotionPropertiesName(
                            new ArrayList<>(
                                Collections.singleton(
                                    new PropertiesTitle(
                                        new PropertiesTitleText(titleFromMyUrl)
                                    )
                                )
                            )
                        )
                    );
            newNotionPage.setProperties(notionPageProperties);

        notionPageResource.createNewPage(newNotionPage);

        return true;
    }
}