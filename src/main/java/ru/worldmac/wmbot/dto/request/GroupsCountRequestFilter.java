package ru.worldmac.wmbot.dto.request;

import lombok.Builder;
import lombok.Getter;
import ru.worldmac.wmbot.dto.enums.GroupFilterEnum;
import ru.worldmac.wmbot.dto.enums.GroupTypeEnum;

/**
 * Request arguments for group count requests.
 */

@Builder
@Getter
public class GroupsCountRequestFilter {
    private final String query;
    private final GroupTypeEnum type;
    private final GroupFilterEnum filter;
}