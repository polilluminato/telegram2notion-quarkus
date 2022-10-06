package com.albertobonacina.telegram2notion.service;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TelegramBotService extends TelegramLongPollingBot {

    @ConfigProperty(name = "telegram.bot.username")
    String botUsername;
    @ConfigProperty(name = "telegram.bot.token")
    String botToken;

    private final INotionService notionService;

    public TelegramBotService(INotionService notionService) {
        this.notionService = notionService;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    public void onUpdateReceived(Update update) {

        String chatId = update.getMessage().getChatId().toString();
        String telegramMessage = update.getMessage().getText();

        Boolean isSent = notionService.send(telegramMessage);

        //Configure message to send back
        //TODO: create a message based on the isSent result
        SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText("My message back");

        try {
            execute(sendMessage);
        } catch (TelegramApiException ignored) {}

    }
}
