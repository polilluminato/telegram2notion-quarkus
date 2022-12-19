package com.albertobonacina.telegram2notion.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotionPageProperties {

    private NotionPropertiesUrl url;
    private NotionPropertiesDate date;
    private NotionPropertiesTag tag;
    private NotionPropertiesName name;

}
