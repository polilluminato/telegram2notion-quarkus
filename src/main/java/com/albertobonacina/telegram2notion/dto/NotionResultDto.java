package com.albertobonacina.telegram2notion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class NotionResultDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -6402172338477512397L;

    private Boolean result;
    private String message;
}
