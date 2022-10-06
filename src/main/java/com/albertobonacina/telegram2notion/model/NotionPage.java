package com.albertobonacina.telegram2notion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotionPage {

    private NotionPageParent parent;
    private NotionPageProperties properties;

}
