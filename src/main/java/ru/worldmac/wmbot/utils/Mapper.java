package ru.worldmac.wmbot.utils;

import org.telegram.telegrambots.meta.api.objects.Chat;
import ru.worldmac.wmbot.entity.TelegramUser;

public class Mapper {
    private Mapper(){
    }

    public static void fullingTelegramUser(TelegramUser telegramUser, Chat userInfo) {
        telegramUser.setChatId(userInfo.getId().toString());
        telegramUser.setUserName(userInfo.getUserName());
        telegramUser.setFirstName(userInfo.getFirstName());
        telegramUser.setLastName(userInfo.getLastName());
    }
}
