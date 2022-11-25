package ru.worldmac.wmbot.command.commands;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.worldmac.wmbot.command.Command;
import ru.worldmac.wmbot.command.CommandUtils;
import ru.worldmac.wmbot.entity.TelegramUser;
import ru.worldmac.wmbot.service.SendMessageService;
import ru.worldmac.wmbot.service.TelegramUserService;
import ru.worldmac.wmbot.utils.Mapper;

/**
 * Start {@link Command}.
 */
public class StartCommand implements Command {

    private final SendMessageService sendMessageService;
    private final TelegramUserService telegramUserService;

    public final static String START_MESSAGE = "Привет, я бот WorldMac. Я помогу тебе в поиске техники";

    public StartCommand(SendMessageService sendMessageService, TelegramUserService telegramUserService) {
        this.sendMessageService = sendMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        var chatInfo = CommandUtils.getChat(update);

        telegramUserService.findByChatId(chatInfo.getId().toString()).ifPresentOrElse(
                user -> {
                    user.setActive(true);
                    telegramUserService.save(user);
                },
                () -> {
                    var telegramUser = new TelegramUser();
                    telegramUser.setActive(true);
                    Mapper.fullingTelegramUser(telegramUser, chatInfo);

                    telegramUserService.save(telegramUser);
                }
        );

        sendMessageService.sendMessage(chatInfo.getId().toString(), START_MESSAGE);
    }
}
