package com.albertobonacina.telegram2notion;

import com.albertobonacina.telegram2notion.service.INotionService;
import com.albertobonacina.telegram2notion.service.TelegramBotService;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MyApp implements QuarkusApplication {

    @Inject
    INotionService notionService;

    @Override
    public int run(String... args) throws Exception {
        startTelegramBot();
        Quarkus.waitForExit();
        return 0;
    }

    private void startTelegramBot() throws TelegramApiException {
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        try {
            api.registerBot(new TelegramBotService(notionService));
        } catch (TelegramApiException ignored) {}
    }
}
