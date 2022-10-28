package ru.worldmac.wmbot.feign;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import ru.worldmac.wmbot.config.FeignClientConfigBuilder;
import ru.worldmac.wmbot.dto.request.GroupRequestFilter;

public class JrGroupClientTest {
    private final String URL = "https://javarush.ru/api/1.0/rest/groups";
    private final JRGroupClient jrGroupClient = FeignClientConfigBuilder.feignBuildJson(JRGroupClient.class, URL);

    @Test
    public void shouldProperlyGetGroupsDiscWithEmptyArgs() {
        //given
        GroupRequestFilter args = GroupRequestFilter.builder().build();

        //when
        var groupList = jrGroupClient.getGroupDiscussionByFilter(args);

        //then
        Assertions.assertNotNull(groupList);
        Assertions.assertFalse(groupList.isEmpty());
    }

    @Test
    public void shouldProperlyGetWithOffSetAndLimit() {
        //given
        GroupRequestFilter args = GroupRequestFilter.builder()
                .offset(1)
                .limit(2)
                .build();

        //when
        var groupList =
                jrGroupClient.getGroupDiscussionByFilter(args);

        //then
        Assertions.assertNotNull(groupList);
        Assertions.assertEquals(2, groupList.size());
        Assertions.assertFalse(groupList.isEmpty());
    }

    @Test
    public void shouldProperlyGetGroupById() {
        //given
        Integer groupId = 1;

        var groupById = jrGroupClient.getGroupById(groupId.toString());

        Assertions.assertEquals(groupId, groupById.getId());
    }
}
