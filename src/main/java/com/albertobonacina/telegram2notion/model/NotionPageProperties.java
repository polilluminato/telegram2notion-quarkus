package com.albertobonacina.telegram2notion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotionPageProperties {

    private NotionPropertiesUrl Url;
    private NotionPropertiesDate Date;
    private NotionPropertiesTag Tag;

}
