package ru.worldmac.wmbot.feign;

import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import ru.worldmac.wmbot.dto.request.GroupRequestFilter;
import ru.worldmac.wmbot.dto.request.GroupsCountRequestFilter;
import ru.worldmac.wmbot.dto.response.GroupDiscussionInfo;

import java.util.List;
import java.util.Map;

/**
 * Client for Javarush Open API corresponds to Groups.
 */
public interface JRGroupClient {

    /**
     * Get all the {@link GroupDiscussionInfo} filtered by provided {@link GroupRequestFilter}.
     *
     * @param args provided {@link GroupRequestFilter}
     * @return the collection of the {@link GroupDiscussionInfo} objects.
     * <p>
     * <p>
     * OK (status code 200) <p>
     * Unauthorized (status code 401) <p>
     * Forbidden (status code 403) <p>
     * Not Found (status code 404) <p>
     */

    @RequestLine("GET ")
    @Headers("Content-Type: application/json")
    List<GroupDiscussionInfo> getGroupDiscussionByFilter(@QueryMap Map<String, Object> args);

    /**
     * Get count of groups filtered by provided {@link GroupsCountRequestFilter}.
     *
     * @param countRequestArgs provided {@link GroupsCountRequestFilter}.
     * @return the count of the groups.
     * <p>
     * <p>
     * OK (status code 200) <p>
     * or Unauthorized (status code 401) <p>
     * or Forbidden (status code 403) <p>
     * or Not Found (status code 404) <p>
     */

    @RequestLine("GET /count")
    @Headers("Content-Type: application/json")
    Integer getGroupCount(@QueryMap Map<String, Object> countRequestArgs);

    /**
     * Get {@link GroupDiscussionInfo} by provided ID.
     *
     * @param id provided ID.
     * @return {@link GroupDiscussionInfo} object.
     * <p>
     * <p>
     * OK (status code 200)<p>
     * or Unauthorized (status code 401)<p>
     * or Forbidden (status code 403)<p>
     * or Not Found (status code 404)<p>
     */

    @RequestLine("GET /group{id}")
    @Headers("Content-Type: application/json")
    GroupDiscussionInfo getGroupById(@Param String id);

}
