package ru.worldmac.wmbot.service;

import org.springframework.stereotype.Service;
import ru.worldmac.wmbot.dto.response.GroupDiscussionInfo;
import ru.worldmac.wmbot.entity.GroupSub;

/**
 * {@link Service} for handling {@link GroupSub} entity.
 */
public interface GroupSubService {

    GroupSub save(String chatId, GroupDiscussionInfo groupInfo);
}
