package ru.worldmac.wmbot.builders;

import ru.worldmac.wmbot.entity.TelegramUser;

public class TelegramUserBuilder {
    private String chatId;
    private boolean isActive;
    private String userName;
    private String firstName;
    private String lastName;
    private boolean isBot;

    private TelegramUserBuilder() {
    }

    public static TelegramUserBuilder builder() {
        return new TelegramUserBuilder();
    }

    public static TelegramUser defaultUser() {
        var user = new TelegramUser();

        user.setChatId("1");
        user.setActive(true);
        user.setUserName("UserName");
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setBot(false);

        return user;
    }

    public TelegramUserBuilder chatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public TelegramUserBuilder isActive(boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public TelegramUserBuilder userName(String userName) {
        this.userName = userName;
        return this;
    }

    public TelegramUserBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public TelegramUserBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public TelegramUserBuilder isBot(boolean isBot) {
        this.isBot = isBot;
        return this;
    }

    public TelegramUser build() {
        var user = new TelegramUser();

        user.setChatId(this.chatId);
        user.setActive(this.isActive);
        user.setUserName(this.userName);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setBot(this.isBot);

        return user;
    }
}
