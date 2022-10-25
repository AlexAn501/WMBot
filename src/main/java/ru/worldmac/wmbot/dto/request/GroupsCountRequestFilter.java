package ru.worldmac.wmbot.dto.request;

import lombok.Builder;
import lombok.Getter;
import ru.worldmac.wmbot.dto.enums.GroupFilterEnum;
import ru.worldmac.wmbot.dto.enums.GroupTypeEnum;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

/**
 * Request arguments for group count requests.
 */

@Builder
@Getter
public class GroupsCountRequestFilter {

    private final String query;
    private final GroupTypeEnum type;
    private final GroupFilterEnum filter;

    public Map<String, Object> populateQueries() {
        Map<String, Object> queries = new HashMap<>();
        if (nonNull(query)) {
            queries.put("query", query);
        }
        if (nonNull(type)) {
            queries.put("type", type);
        }
        if (nonNull(filter)) {
            queries.put("filter", filter);
        }
        return queries;
    }
}