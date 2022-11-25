package ru.worldmac.wmbot.command.commands;

import org.junit.jupiter.api.DisplayName;
import ru.worldmac.wmbot.command.Command;
import ru.worldmac.wmbot.command.enums.CommandName;

@DisplayName("Unit-level testing for StopCommand")
public class StopCommandTest extends AbstractCommandTest{
    @Override
    String getCommandName() {
        return CommandName.STOP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return StopCommand.STOP_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new StopCommand(sendMessageService, telegramUserService);
    }
}