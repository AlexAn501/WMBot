package ru.worldmac.wmbot.comand.commands;

import org.apache.commons.lang3.StringUtils;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.worldmac.wmbot.comand.Command;
import ru.worldmac.wmbot.comand.enums.CommandName;
import ru.worldmac.wmbot.dto.request.GroupRequestFilter;
import ru.worldmac.wmbot.dto.response.GroupDiscussionInfo;
import ru.worldmac.wmbot.entity.GroupSub;
import ru.worldmac.wmbot.feign.JRGroupClient;
import ru.worldmac.wmbot.service.GroupSubService;
import ru.worldmac.wmbot.service.SendMessageService;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Add Group subscription {@link Command}.
 */
public class AddGroupSubCommand implements Command {

    private final SendMessageService sendMessageService;
    private final JRGroupClient jRGroupClient;
    private final GroupSubService groupSubService;

    public AddGroupSubCommand(SendMessageService sendMessageService, JRGroupClient jRGroupClient, GroupSubService groupSubService) {
        this.sendMessageService = sendMessageService;
        this.jRGroupClient = jRGroupClient;
        this.groupSubService = groupSubService;
    }

    @Override
    public void execute(Update update) {
        Message message = update.getMessage();
        if (CommandName.ADD_GROUP_SUB.getCommandName().equalsIgnoreCase(message.getText())) {
            sendGroupIdList(message.getChatId().toString());
            return;
        }

        String groupId = message.getText().split(" ")[1];
        String chatId = message.getChatId().toString();

        if(StringUtils.isNumeric(groupId)) {
            GroupDiscussionInfo groupById = jRGroupClient.getGroupById(groupId);
            if (Objects.isNull(groupById)) {
                sendGroupNotFound(chatId, groupId);
            }
            GroupSub savedGroupSub = groupSubService.save(chatId, groupById);
            sendMessageService.sendMessage(chatId, "Подписал на группу " + savedGroupSub.getTitle());
        }else {
            sendGroupNotFound(chatId, groupId);
        }
    }

    private void sendGroupNotFound(String chatId, String groupId) {
        String groupNotFoundMessage = "Нет группы с ID = \"%s\"";
        sendMessageService.sendMessage(chatId, String.format(groupNotFoundMessage, groupId));
    }

    private void sendGroupIdList(String chatId) {
        GroupRequestFilter filter = GroupRequestFilter.builder().build();
        String groupIds = jRGroupClient.getGroupDiscussionByFilter(filter).stream()
                .map(group -> String.format("%s - %s \n", group.getTitle(), group.getId()))
                .collect(Collectors.joining());

        String message = "Чтобы подписаться на группу - передай комадну вместе с ID группы. \n" +
                "Например: /addGroupSub 16. \n\n" +
                "я подготовил список всех групп - выбирай какую хочешь :) \n\n" +
                "имя группы - ID группы \n\n" +
                "%s";

        sendMessageService.sendMessage(chatId, String.format(message, groupIds));
    }
}
