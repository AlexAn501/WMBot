package ru.worldmac.wmbot.feign;

import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import ru.worldmac.wmbot.dto.request.PostCountRequestFilter;
import ru.worldmac.wmbot.dto.request.PostsRequestFilter;
import ru.worldmac.wmbot.dto.response.PostInfo;

import java.util.List;

/**
 * Client for Javarush Open API corresponds to Posts.
 */
public interface JRPostsClient {

    /**
     * Get all the {@link PostInfo} filtered by provided {@link PostsRequestFilter}.
     *
     * @param args provided {@link PostsRequestFilter}
     * @return the collection of the {@link PostInfo} objects.
     * <p>
     * <p>
     * OK (status code 200)<p>
     * Unauthorized (status code 401)<p>
     * Forbidden (status code 403)<p>
     * Not Found (status code 404)<p>
     */

    @RequestLine("GET ")
    @Headers("Content-Type: application/json")
    List<PostInfo> getPostsByFilter(@QueryMap PostsRequestFilter args);

    /**
     * Get count of post filtered by provided {@link PostCountRequestFilter}.
     *
     * @param args provided {@link PostCountRequestFilter}.
     * @return the count of the post.
     * <p>
     * <p>
     * OK (status code 200) <p>
     * or Unauthorized (status code 401) <p>
     * or Forbidden (status code 403) <p>
     * or Not Found (status code 404) <p>
     */

    @RequestLine("GET /count")
    @Headers("Content-Type: application/json")
    Integer getPostCount(@QueryMap PostCountRequestFilter args);

    /**
     * Get {@link PostInfo} by provided ID.
     *
     * @param id provided ID.
     * @return {@link PostInfo} object.
     * <p>
     * <p>
     * OK (status code 200)<p>
     * or Unauthorized (status code 401)<p>
     * or Forbidden (status code 403)<p>
     * or Not Found (status code 404)<p>
     */

    @RequestLine("GET /post{id}")
    @Headers("Content-Type: application/json")
    PostInfo getPostById(@Param String id);
}
