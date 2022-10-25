package ru.worldmac.wmbot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.worldmac.wmbot.bot.WmBot;

/**
 * Implementation of {@link SendMessageService} interface.
 */
@Slf4j
@Service
public class SendMessageServiceImpl implements SendMessageService {

    private final WmBot wmBot;

    public SendMessageServiceImpl(WmBot wmBot) {
        this.wmBot = wmBot;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sm = new SendMessage();
        sm.setChatId(chatId);
        sm.setText(message);
        sm.enableHtml(true);


        try {
            wmBot.execute(sm);
//            log.info(sm.getText());
        } catch (TelegramApiException e) {
            log.info(e.toString());
            e.printStackTrace();
        }
    }
}
