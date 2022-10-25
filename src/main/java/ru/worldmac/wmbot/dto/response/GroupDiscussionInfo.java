package ru.worldmac.wmbot.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.worldmac.wmbot.dto.UserDiscussionInfo;

/**
 * Group discussion info class.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class GroupDiscussionInfo extends GroupInfo {

    private UserDiscussionInfo userDiscussionInfo;
    private Integer commentsCount;
}