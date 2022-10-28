package ru.worldmac.wmbot.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import ru.worldmac.wmbot.dto.response.GroupDiscussionInfo;
import ru.worldmac.wmbot.entity.GroupSub;
import ru.worldmac.wmbot.entity.TelegramUser;
import ru.worldmac.wmbot.builders.TelegramUserBuilder;
import ru.worldmac.wmbot.repository.GroupSubRepository;

import java.util.Optional;

@DisplayName("Unit-level testing for GroupSubService")
public class GroupSubServiceTest {

    private GroupSubService groupSubService;
    private GroupSubRepository groupSubRepository;
    private TelegramUser newUser;

    @BeforeEach
    public void init() {
        var telegramUserService = Mockito.mock(TelegramUserService.class);
        groupSubRepository = Mockito.mock(GroupSubRepository.class);
        groupSubService = new GroupSubServiceImpl(groupSubRepository, telegramUserService);

        newUser = TelegramUserBuilder.defaultUser();

        Mockito.when(telegramUserService.findByChatId(newUser.getChatId()))
                .thenReturn(Optional.of(newUser));
    }

    @Test
    public void shouldProperlySaveGroup() {

        var groupDiscussionInfo = new GroupDiscussionInfo();
        groupDiscussionInfo.setId(1);
        groupDiscussionInfo.setTitle("g1");
        var expectedGroupSub = new GroupSub();
        expectedGroupSub.setId(groupDiscussionInfo.getId());
        expectedGroupSub.setTitle(groupDiscussionInfo.getTitle());
        expectedGroupSub.addUser(newUser);

        groupSubService.save(newUser.getChatId(), groupDiscussionInfo);

        Mockito.verify(groupSubRepository).save(expectedGroupSub);
    }

    @Test
    public void shouldProperlyAddToExistingGroup() {
        //given
        var oldUser = TelegramUserBuilder.builder()
                .chatId("2").build();

        var groupDiscussionInfo = new GroupDiscussionInfo();
        groupDiscussionInfo.setId(1);
        groupDiscussionInfo.setTitle("g1");

        var groupFromDb = new GroupSub();
        groupFromDb.setId(groupDiscussionInfo.getId());
        groupFromDb.setTitle(groupDiscussionInfo.getTitle());
        groupFromDb.addUser(oldUser);

        Mockito.when(groupSubRepository.findById(groupDiscussionInfo.getId()))
                .thenReturn(Optional.of(groupFromDb));

        var expectedGroup = new GroupSub();
        expectedGroup.setId(groupDiscussionInfo.getId());
        expectedGroup.setTitle(groupDiscussionInfo.getTitle());
        expectedGroup.addUser(oldUser);
        expectedGroup.addUser(newUser);

        //when
        groupSubService.save(newUser.getChatId(), groupDiscussionInfo);

        //then
        Mockito.verify(groupSubRepository).findById(groupDiscussionInfo.getId());
        Mockito.verify(groupSubRepository).save(expectedGroup);
    }
}
