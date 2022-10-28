package ru.worldmac.wmbot.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.worldmac.wmbot.dto.MeGroupInfo;
import ru.worldmac.wmbot.dto.UserDiscussionInfo;

/**
 * Group Info DTO class.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class GroupInfo extends Info {

    private String avatarUrl;
    private Integer levelToEditor;
    private MeGroupInfo meGroupInfo;
    private Integer usersCount;
    private UserDiscussionInfo userDiscussionInfo;
    private Integer commentsCount;

}
