package ru.worldmac.wmbot.dto.request;

import lombok.Builder;
import lombok.Getter;
import ru.worldmac.wmbot.dto.enums.GroupFilterEnum;
import ru.worldmac.wmbot.dto.enums.GroupTypeEnum;
import ru.worldmac.wmbot.dto.enums.PostTypeEnum;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

/**
 * Request arguments for Post request.
 */

@Getter
@Builder
public class PostsRequestFilter {
    private final String query;
    private final PostTypeEnum type;
    private final String groupKid;
    private final GroupTypeEnum groupType;
    private final Integer ownerId;
    private final String order;
    private final GroupFilterEnum filter;
    private final Integer offset;
    private final Integer limit;

    public Map<String, Object> populateQueries() {
        Map<String, Object> queries = new HashMap<>();
        if (nonNull(query)) {
            queries.put("query", query);
        }
        if (nonNull(type)) {
            queries.put("type", type);
        }
        if (nonNull(groupKid)) {
            queries.put("groupKid", groupKid);
        }
        if (nonNull(groupType)) {
            queries.put("groupType", groupType);
        }
        if (nonNull(ownerId)) {
            queries.put("ownerId", ownerId);
        }
        if (nonNull(order)) {
            queries.put("order", order);
        }
        if (nonNull(filter)) {
            queries.put("filter", filter);
        }
        if (nonNull(offset)) {
            queries.put("offset", offset);
        }
        if (nonNull(limit)) {
            queries.put("limit", limit);
        }
        return queries;
    }
}