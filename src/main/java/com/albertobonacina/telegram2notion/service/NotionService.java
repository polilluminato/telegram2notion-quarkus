package com.albertobonacina.telegram2notion.service;

import com.albertobonacina.telegram2notion.model.*;
import com.albertobonacina.telegram2notion.utils.UrlUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class NotionService implements INotionService {

    @ConfigProperty(name = "notion.db")
    String notionDB;
    @ConfigProperty(name = "notion.secret")
    String notionSecret;
    @ConfigProperty(name = "notion.version")
    String notionVersion;

    @Override
    public Boolean send(String telegramMessage) {

        //Get urls inside message
        List<String> urlList = UrlUtils.extractUrls(telegramMessage);
        List<String> hashtagList = UrlUtils.extractHashTags(telegramMessage);

        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Europe/Rome"));

        NotionPage notionPageToCreate = new NotionPage();
            notionPageToCreate.setParent(new NotionPageParent(notionDB));
                NotionPageProperties notionPageProperties = new NotionPageProperties();
                    notionPageProperties.setUrl(
                        new NotionPropertiesUrl(urlList.get(0))
                    );
                    notionPageProperties.setDate(
                        new NotionPropertiesDate(
                            new PropertiesDateDate(now.toString(),null))
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
                                        new PropertiesTitleText("Test title page")
                                    )
                                )
                            )
                        )
                    );
            notionPageToCreate.setProperties(notionPageProperties);

        //TODO: send notionPageToCreate to Notion via NotionApi

        System.out.println("notionPageToCreate: " + notionPageToCreate);

        return true;
    }
}