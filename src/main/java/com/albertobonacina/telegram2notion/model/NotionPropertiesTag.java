package com.albertobonacina.telegram2notion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotionPropertiesTag {

    private List<PropertiesMultiSelect> multi_select;

}
