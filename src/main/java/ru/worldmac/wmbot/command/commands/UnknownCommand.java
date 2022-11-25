package ru.worldmac.wmbot.command.commands;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.worldmac.wmbot.command.Command;
import ru.worldmac.wmbot.command.CommandUtils;
import ru.worldmac.wmbot.service.SendMessageService;

/**
 * Unknown {@link Command}.
 */

public class UnknownCommand implements Command {

    private final SendMessageService sendMessageService;

    public final static String UNKNOWN_MESSAGE = "Я не понимаю вас \uD83D\uDE1F, напишите /help чтобы узнать что я понимаю.";

    public UnknownCommand(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    @Override
    public void execute(Update update) {
        var chatId = CommandUtils.getChatId(update);
        sendMessageService.sendMessage(chatId, UNKNOWN_MESSAGE);
    }
}
