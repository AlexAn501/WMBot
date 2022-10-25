package ru.worldmac.wmbot.dto.request;

import lombok.Getter;
import lombok.Setter;
import ru.worldmac.wmbot.dto.enums.GroupFilterEnum;
import ru.worldmac.wmbot.dto.enums.GroupTypeEnum;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

@Setter
@Getter
public abstract class Filter {

    private String query;
    private GroupTypeEnum type;
    private GroupFilterEnum filter;

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
