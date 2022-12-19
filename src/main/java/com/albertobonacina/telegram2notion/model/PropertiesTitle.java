package com.albertobonacina.telegram2notion.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PropertiesTitle {

    private String type;
    private PropertiesTitleText text;

    public PropertiesTitle(PropertiesTitleText text){
        this.type = "text";
        this.text = text;
    }

}
