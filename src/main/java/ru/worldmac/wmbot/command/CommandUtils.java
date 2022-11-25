package ru.worldmac.wmbot.command;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CommandUtils {
    private CommandUtils(){}

    public static String getChatId(Update update){
        return update.getMessage().getChatId().toString();
    }

    public static Chat getChat(Update update){
        return update.getMessage().getChat();
    }

    public static Message getMessage(Update update){
        return update.getMessage();
    }
}
