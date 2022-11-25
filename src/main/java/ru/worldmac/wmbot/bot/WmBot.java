package ru.worldmac.wmbot.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.worldmac.wmbot.command.CommandContainer;
import ru.worldmac.wmbot.feign.JRGroupClient;
import ru.worldmac.wmbot.feign.JRPostsClient;
import ru.worldmac.wmbot.service.GroupSubService;
import ru.worldmac.wmbot.service.SendMessageServiceImpl;
import ru.worldmac.wmbot.service.TelegramUserService;

import static ru.worldmac.wmbot.command.enums.CommandName.NO;

/**
 * Telegram bot for WorldMac.
 */
@Component
public class WmBot extends TelegramLongPollingBot {

    private final static String COMMAND_PREFIX = "/";

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    private final CommandContainer commandContainer;

    public WmBot(TelegramUserService telegramUserService, GroupSubService groupSubService,
                 JRPostsClient jrPostsClient, JRGroupClient jrGroupClient) {
        this.commandContainer = new CommandContainer(new SendMessageServiceImpl(this),
                telegramUserService,groupSubService, jrGroupClient);
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();

                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
            }
        }
    }
}
