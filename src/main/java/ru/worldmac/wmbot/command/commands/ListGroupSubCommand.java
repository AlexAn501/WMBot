package ru.worldmac.wmbot.command.commands;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.worldmac.wmbot.command.Command;
import ru.worldmac.wmbot.command.CommandUtils;
import ru.worldmac.wmbot.entity.GroupSub;
import ru.worldmac.wmbot.service.SendMessageService;
import ru.worldmac.wmbot.service.TelegramUserService;

import javax.ws.rs.NotFoundException;
import java.util.stream.Collectors;

/**
 * {@link Command} for getting list of {@link GroupSub}.
 */
public class ListGroupSubCommand implements Command {

    private final SendMessageService sendMessageService;
    private final TelegramUserService telegramUserService;

    public ListGroupSubCommand(SendMessageService sendMessageService, TelegramUserService telegramUserService) {
        this.sendMessageService = sendMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        var user = telegramUserService.findByChatId(CommandUtils.getChatId(update))
                .orElseThrow(NotFoundException::new);

        var message = "Я нашел все подписки на группы: \n\n";
        var pattern = "Группа: %s, ID = %s\n";
        var collectedGroups = user.getGroupSubs().stream()
                .map(it -> String.format(pattern, it.getTitle(), it.getId()))
                .collect(Collectors.joining());

        sendMessageService.sendMessage(user.getChatId(), message + collectedGroups);
    }
}
