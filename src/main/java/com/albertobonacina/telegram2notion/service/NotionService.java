package com.albertobonacina.telegram2notion.service;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NotionService implements INotionService {

    @Override
    public Boolean send(String telegramMessage) {
        System.out.println("telegramMessage: " + telegramMessage);
        return true;
    }
}