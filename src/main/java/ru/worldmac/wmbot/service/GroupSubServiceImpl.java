package ru.worldmac.wmbot.service;

import org.springframework.stereotype.Service;
import ru.worldmac.wmbot.dto.response.GroupDiscussionInfo;
import ru.worldmac.wmbot.entity.GroupSub;
import ru.worldmac.wmbot.repository.GroupSubRepository;

import javax.ws.rs.NotFoundException;
import java.util.Optional;

@Service
public class GroupSubServiceImpl implements GroupSubService {

    private final GroupSubRepository groupSubRepository;
    private final TelegramUserService telegramUserService;

    public GroupSubServiceImpl(GroupSubRepository groupSubRepository, TelegramUserService telegramUserService) {
        this.groupSubRepository = groupSubRepository;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public GroupSub save(String chatId, GroupDiscussionInfo groupInfo) {
        var telegramUser = telegramUserService.findByChatId(chatId).orElseThrow(NotFoundException::new);

        GroupSub groupSub;
        Optional<GroupSub> groupSubFromDb = groupSubRepository.findById(groupInfo.getId());
        if (groupSubFromDb.isPresent()) {
            groupSub = groupSubFromDb.get();
            var user = groupSub.getUsers().stream()
                    .filter(u -> u.getChatId().equalsIgnoreCase(chatId))
                    .findFirst();
            if (user.isEmpty()) {
                groupSub.addUser(telegramUser);
            }
        }else {
            groupSub = new GroupSub();
            groupSub.addUser(telegramUser);
            groupSub.setId(groupInfo.getId());
            groupSub.setTitle(groupInfo.getTitle());
            //?
            groupSub.setLastArticleId(groupInfo.getLevelToEditor());
        }

        return groupSubRepository.save(groupSub);
    }
}
