package com.albertobonacina.telegram2notion.service;

import com.albertobonacina.telegram2notion.dto.NotionResultDto;

public interface INotionService {

    NotionResultDto send(String telegramMessage);

}