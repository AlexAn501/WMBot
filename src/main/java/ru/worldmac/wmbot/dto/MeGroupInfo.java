package ru.worldmac.wmbot.dto;

import lombok.Data;
import ru.worldmac.wmbot.dto.enums.GroupInfoStatusEnum;

/**
 * Group information related to authorized user. If there is no user - will be null.
 */
@Data
public class MeGroupInfo {

    private GroupInfoStatusEnum status;
    private String userGroupId;
}
