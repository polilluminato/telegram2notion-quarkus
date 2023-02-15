package com.albertobonacina.telegram2notion.dto;

import java.io.Serializable;

public record NotionResultDto(Boolean result, String message) implements Serializable {}
