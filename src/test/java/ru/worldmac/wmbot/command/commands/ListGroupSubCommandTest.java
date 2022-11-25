package ru.worldmac.wmbot.command.commands;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.worldmac.wmbot.builders.TelegramUserBuilder;
import ru.worldmac.wmbot.entity.GroupSub;
import ru.worldmac.wmbot.service.SendMessageService;
import ru.worldmac.wmbot.service.TelegramUserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.worldmac.wmbot.command.enums.CommandName.LIST_GROUP_SUB;

@DisplayName("Unit-level testing for ListGroupSubCommand")
public class ListGroupSubCommandTest {

    @Test
    public void shouldProperlyShowsListGroupSub() {
        //given
        var user = TelegramUserBuilder.defaultUser();

        var groupSubList = List.of(
                populateGroupSub(1, "gs1"),
                populateGroupSub(2, "gs2"),
                populateGroupSub(3, "gs3"),
                populateGroupSub(4, "gs4"));

        user.setGroupSubs(groupSubList);

        var sendBotMessageService = Mockito.mock(SendMessageService.class);
        var telegramUserService = Mockito.mock(TelegramUserService.class);

        Mockito.when(telegramUserService.findByChatId(user.getChatId())).thenReturn(Optional.of(user));

        var command = new ListGroupSubCommand(sendBotMessageService, telegramUserService);

        Update update = new Update();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getChatId()).thenReturn(Long.valueOf(user.getChatId()));
        Mockito.when(message.getText()).thenReturn(LIST_GROUP_SUB.getCommandName());
        update.setMessage(message);

        var collectedGroups = "Я нашел все подписки на группы: \n\n" +
                user.getGroupSubs().stream()
                        .map(it -> String.format("Группа: %s, ID = %s\n", it.getTitle(), it.getId()))
                        .collect(Collectors.joining());

        //when
        command.execute(update);

        //then
        Mockito.verify(sendBotMessageService).sendMessage(user.getChatId(), collectedGroups);
    }

    private GroupSub populateGroupSub(Integer id, String title) {
        GroupSub gs = new GroupSub();
        gs.setId(id);
        gs.setTitle(title);
        return gs;
    }
}
