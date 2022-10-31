package ru.worldmac.wmbot.comand.commands;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.worldmac.wmbot.comand.Command;
import ru.worldmac.wmbot.dto.enums.GroupTypeEnum;
import ru.worldmac.wmbot.dto.enums.PostTypeEnum;
import ru.worldmac.wmbot.dto.request.GroupRequestFilter;
import ru.worldmac.wmbot.dto.request.GroupsCountRequestFilter;
import ru.worldmac.wmbot.dto.request.PostCountRequestFilter;
import ru.worldmac.wmbot.dto.request.PostsRequestFilter;
import ru.worldmac.wmbot.dto.response.GroupDiscussionInfo;
import ru.worldmac.wmbot.dto.response.PostInfo;
import ru.worldmac.wmbot.entity.TelegramUser;
import ru.worldmac.wmbot.feign.JRGroupClient;
import ru.worldmac.wmbot.feign.JRPostsClient;
import ru.worldmac.wmbot.service.SendMessageService;
import ru.worldmac.wmbot.service.TelegramUserService;
import ru.worldmac.wmbot.utils.Mapper;

import java.util.List;

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
        var chatInfo = update.getMessage().getChat();

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
