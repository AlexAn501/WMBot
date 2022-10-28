package ru.worldmac.wmbot.command.commands;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.worldmac.wmbot.bot.WmBot;
import ru.worldmac.wmbot.comand.Command;
import ru.worldmac.wmbot.feign.JRGroupClient;
import ru.worldmac.wmbot.feign.JRPostsClient;
import ru.worldmac.wmbot.service.SendMessageService;
import ru.worldmac.wmbot.service.SendMessageServiceImpl;
import ru.worldmac.wmbot.service.TelegramUserService;

/**
 * Abstract class for testing {@link Command}s.
 */
public abstract class AbstractCommandTest {

    protected WmBot wmBot = Mockito.mock(WmBot.class);
    protected SendMessageService sendMessageService = new SendMessageServiceImpl(wmBot);
    protected TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);
    protected JRPostsClient jrPostsClient = Mockito.mock(JRPostsClient.class);
    protected JRGroupClient jrGroupClient = Mockito.mock(JRGroupClient.class);

    abstract String getCommandName();

    abstract String getCommandMessage();

    abstract Command getCommand();

    @Test
    public void shouldPropertyExecuteCommand() throws TelegramApiException {
        //given
        Long chatId = 12345678L;

        Update update = new Update();
        Message message = Mockito.mock(Message.class);
        Chat chatInfo = Mockito.mock(Chat.class);
        update.setMessage(message);

        Mockito.when(chatInfo.getId()).thenReturn(chatId);
        Mockito.when(message.getText()).thenReturn(getCommandName());
        Mockito.when(update.getMessage().getChat()).thenReturn(chatInfo);
        Mockito.when(update.getMessage().getChatId()).thenReturn(chatId);

        SendMessage sm = new SendMessage();
        sm.setChatId(chatId.toString());
        sm.setText(getCommandMessage());
        sm.enableHtml(true);

        //when
        getCommand().execute(update);

        //then
        Mockito.verify(wmBot).execute(sm);
    }
}
