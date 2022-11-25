package ru.worldmac.wmbot.command.commands;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.worldmac.wmbot.command.Command;
import ru.worldmac.wmbot.command.CommandUtils;
import ru.worldmac.wmbot.service.SendMessageService;

/**
 * List {@link Command}.
 */

public class ListCommand implements Command {

    private final SendMessageService sendMessageService;

    public final static String LIST_MESSAGE = "Список всех продуктов.";

    public ListCommand(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    @Override
    public void execute(Update update) {
        var chatId = CommandUtils.getChatId(update);
        sendMessageService.sendMessage(chatId, LIST_MESSAGE);
    }
}
