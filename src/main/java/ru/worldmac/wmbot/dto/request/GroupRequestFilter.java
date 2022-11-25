package ru.worldmac.wmbot.dto.request;

import lombok.Builder;
import lombok.Getter;
import ru.worldmac.wmbot.dto.enums.GroupFilterEnum;
import ru.worldmac.wmbot.dto.enums.GroupTypeEnum;

/**
 * Request arguments for group requests.
 */

@Builder
@Getter
public class GroupRequestFilter {

    /**
     * specified where to start getting groups
     */
    private final Integer offset;
    /**
     * Limited number of groups.
     */
    private final Integer limit;

    private final String query;
    private final GroupTypeEnum type;
    private final GroupFilterEnum filter;
}