package ru.worldmac.wmbot.dto.request;

import lombok.Builder;
import lombok.Getter;
import ru.worldmac.wmbot.dto.enums.GroupTypeEnum;
import ru.worldmac.wmbot.dto.enums.PostTypeEnum;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

/**
 * Request arguments for posts count requests.
 */

@Getter
@Builder
public class PostCountRequestFilter {
    private final String query;
    private final PostTypeEnum type;
    private final String groupKid;
    private final GroupTypeEnum groupType;
    private final Integer ownerId;
    private final String filter;

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
        return queries;
    }
}
