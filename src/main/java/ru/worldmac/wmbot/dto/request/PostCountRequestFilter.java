package ru.worldmac.wmbot.dto.request;

import lombok.Builder;
import lombok.Getter;
import ru.worldmac.wmbot.dto.enums.GroupTypeEnum;
import ru.worldmac.wmbot.dto.enums.PostTypeEnum;

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
}
