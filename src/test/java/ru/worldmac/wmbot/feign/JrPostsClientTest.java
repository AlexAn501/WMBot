package ru.worldmac.wmbot.feign;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import ru.worldmac.wmbot.config.FeignClientConfigBuilder;
import ru.worldmac.wmbot.dto.request.PostsRequestFilter;

public class JrPostsClientTest {
    private final String url = "https://javarush.ru/api/1.0/rest/posts";
    private final JRPostsClient jrPostsClient = FeignClientConfigBuilder.feignBuildJson(JRPostsClient.class, url);

    @Test
    public void shouldProperlyGetGroupsDiscWithEmptyArgs() {
        //given
        PostsRequestFilter postFilter = PostsRequestFilter.builder()
                .offset(3)
                .limit(3)
                .build();

        //when
        var postsList = jrPostsClient.getPostsByFilter(postFilter);

        //then
        Assertions.assertNotNull(postsList);
        Assertions.assertFalse(postsList.isEmpty());
    }
//
//    @Test
//    public void shouldProperlyGetWithOffSetAndLimit() {
//        //given
//        GroupRequestFilter args = GroupRequestFilter.builder()
//                .offset(1)
//                .limit(2)
//                .build();
//
//        //when
//        var groupList =
//                jrGroupClient.getGroupDiscussionByFilter(args.populateQueries());
//
//        //then
//        Assertions.assertNotNull(groupList);
//        Assertions.assertEquals(2, groupList.size());
//        Assertions.assertFalse(groupList.isEmpty());
//    }

//    @Test
//    public void shouldProperlyGetGroupById() {
//        //given
//        String groupId = "1";
//
//        var groupById = jrGroupClient.getGroupById(groupId);
//
//        Assertions.assertEquals(groupId, groupById.getId());
//    }
}
