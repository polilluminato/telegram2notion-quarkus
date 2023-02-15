package com.albertobonacina.telegram2notion.model;

public record PropertiesTitle(String type, PropertiesTitleText text) {

    public PropertiesTitle(PropertiesTitleText text) {
        this("text", text);
    }

}