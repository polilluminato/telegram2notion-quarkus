package com.albertobonacina.telegram2notion.model;

public record NotionPageProperties(NotionPropertiesUrl url, NotionPropertiesDate date, NotionPropertiesTag tag,
                                   NotionPropertiesName name) {
}