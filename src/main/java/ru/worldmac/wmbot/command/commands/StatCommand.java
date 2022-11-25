package ru.worldmac.wmbot.command.commands;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.worldmac.wmbot.command.Command;
import ru.worldmac.wmbot.command.CommandUtils;
import ru.worldmac.wmbot.service.SendMessageService;
import ru.worldmac.wmbot.service.TelegramUserService;

/**
 * Statistics {@link Command}.
 */
public class StatCommand implements Command {

    private final SendMessageService sendMessageService;
    private final TelegramUserService telegramUserService;

    public final static String STAT_MESSAGE = "WMBot использует %s человек.";

    public StatCommand(SendMessageService sendMessageService, TelegramUserService telegramUserService) {
        this.sendMessageService = sendMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        var chatId = CommandUtils.getChatId(update);
        var sizeActiveUser = telegramUserService.retrieveAllActiveUsers().size();

        sendMessageService.sendMessage(chatId,
                String.format(STAT_MESSAGE, sizeActiveUser));
    }
}
