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
    private final JRPostsClient jrPostsClient;
    private final JRGroupClient jrGroupClient;

    public final static String START_MESSAGE = "Привет, я бот WorldMac. Я помогу тебе в поиске техники";

    public StartCommand(SendMessageService sendMessageService, TelegramUserService telegramUserService,
                        JRPostsClient jrPostsClient,
                        JRGroupClient jrGroupClient) {
        this.sendMessageService = sendMessageService;
        this.telegramUserService = telegramUserService;
        this.jrPostsClient = jrPostsClient;
        this.jrGroupClient = jrGroupClient;
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

        GroupRequestFilter args = GroupRequestFilter.builder()
                .type(GroupTypeEnum.TECH)
                .limit(2)
                .build();
        List<GroupDiscussionInfo> groupDiscussionByFilter = jrGroupClient.getGroupDiscussionByFilter(args);

        GroupsCountRequestFilter countFilter = GroupsCountRequestFilter.builder()
                .type(GroupTypeEnum.TECH)
                .build();
        Integer groupCount = jrGroupClient.getGroupCount(countFilter);

        GroupDiscussionInfo groupById = jrGroupClient.getGroupById("26");

        PostsRequestFilter postFilter = PostsRequestFilter.builder()
                .offset(3)
                .limit(3)
                .build();
        List<PostInfo> postsByFilter = jrPostsClient.getPostsByFilter(postFilter);

        PostInfo postById = jrPostsClient.getPostById("2");

        PostCountRequestFilter postCountFilter = PostCountRequestFilter.builder()
                .type(PostTypeEnum.INNER_LINK)
                .build();
        Integer postCount = jrPostsClient.getPostCount(postCountFilter);


        sendMessageService.sendMessage(chatInfo.getId().toString(), START_MESSAGE);
    }
}
