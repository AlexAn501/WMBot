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

public class GroupSubRepositoryTest  extends TestContainersConfig {

    @Autowired
    private GroupSubRepository groupRepository;

    @Sql(scripts = {"/sql/clearDbs.sql", "/sql/fiveGroupSubsForUser.sql"})
    @Test
    public void shouldProperlyGetAllUsersForGroupSub() {
        //when
        Optional<GroupSub> groupSubFromDB = groupRepository.findById(1);

        //then
        Assertions.assertTrue(groupSubFromDB.isPresent());
        Assertions.assertEquals(1, groupSubFromDB.get().getId());
        List<TelegramUser> users = groupSubFromDB.get().getUsers();
        for (int i = 0; i < users.size(); i++) {
            Assertions.assertEquals(String.valueOf(i + 1), users.get(i).getChatId());
            Assertions.assertTrue(users.get(i).isActive());
        }
    }
}
