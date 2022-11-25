package ru.worldmac.wmbot.repository;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import ru.worldmac.wmbot.entity.GroupSub;
import ru.worldmac.wmbot.entity.TelegramUser;
import ru.worldmac.wmbot.testConfig.TestContainersConfig;

import java.util.List;
import java.util.Optional;

/**
 * Integration-level testing for {@link TelegramUserRepository}.
 */

public class TelegramUserRepositoryTest extends TestContainersConfig {

    @Autowired
    private TelegramUserRepository telegramUserRepository;

    @Sql(scripts = {"/sql/clearDbs.sql", "/sql/telegramUsers.sql"})
    @Test
    public void shouldProperlyFindAllActiveUsers() {
        List<TelegramUser> users = telegramUserRepository.findAllByIsActiveTrue();

        Assertions.assertEquals(5, users.size());
    }

    @Sql(scripts = {"/sql/clearDbs.sql"})
    @Test
    public void shouldProperlySaveTelegramUser() {
        TelegramUser user = new TelegramUser();
        user.setChatId("1234567890");
        user.setActive(false);
        telegramUserRepository.save(user);

        Optional<TelegramUser> saved = telegramUserRepository.findById(user.getChatId());

        Assertions.assertTrue(saved.isPresent());
        Assertions.assertEquals(user, saved.get());
    }

    @Sql(scripts = {"/sql/clearDbs.sql", "/sql/fiveGroupSubsForUser.sql"})
    @Test
    public void shouldProperlyGetAllGroupSubsForUser() {
        //when
        Optional<TelegramUser> userFromDB = telegramUserRepository.findById("1");

        //then
        Assertions.assertTrue(userFromDB.isPresent());
        List<GroupSub> groupSubs = userFromDB.get().getGroupSubs();
        for (int i = 0; i < groupSubs.size(); i++) {
            Assertions.assertEquals(String.format("g%s", (i + 1)), groupSubs.get(i).getTitle());
            Assertions.assertEquals(i + 1, groupSubs.get(i).getId());
            Assertions.assertEquals(i + 1, groupSubs.get(i).getLastArticleId());
        }
    }
}
