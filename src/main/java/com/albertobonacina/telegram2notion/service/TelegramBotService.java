package com.albertobonacina.telegram2notion.service;

import com.albertobonacina.telegram2notion.dto.NotionResultDto;
import org.eclipse.microprofile.config.ConfigProvider;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TelegramBotService extends TelegramLongPollingBot {

    private final INotionService notionService;

    public TelegramBotService(INotionService notionService) {
        this.notionService = notionService;
    }

    @Override
    public String getBotUsername() {
        return ConfigProvider.getConfig().getValue("telegram.bot.username", String.class);
    }

    @Override
    public String getBotToken() {
        return ConfigProvider.getConfig().getValue("telegram.bot.token", String.class);
    }

    public void onUpdateReceived(Update update) {

        String chatId = update.getMessage().getChatId().toString();
        String telegramMessage = update.getMessage().getText();

        NotionResultDto notionResult = notionService.send(telegramMessage);

        //Configure message to send back
        SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText(notionResult.message());

        try {
            execute(sendMessage);
        } catch (TelegramApiException ignored) {}

    }
}
