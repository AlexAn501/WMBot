package ru.worldmac.wmbot.command.commands;

import org.junit.jupiter.api.DisplayName;
import ru.worldmac.wmbot.comand.Command;
import ru.worldmac.wmbot.comand.commands.StartCommand;
import ru.worldmac.wmbot.comand.enums.CommandName;

@DisplayName("Unit-level testing for StartCommand")
public class StartCommandTest extends AbstractCommandTest{
    @Override
    String getCommandName() {
        return CommandName.START.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return StartCommand.START_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new StartCommand(sendMessageService, telegramUserService, jrPostsClient, jrGroupClient);
    }
}