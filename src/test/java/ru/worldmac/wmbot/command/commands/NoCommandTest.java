package ru.worldmac.wmbot.command.commands;

import org.junit.jupiter.api.DisplayName;
import ru.worldmac.wmbot.command.Command;
import ru.worldmac.wmbot.command.enums.CommandName;

@DisplayName("Unit-level testing for NoCommand")
public class NoCommandTest extends AbstractCommandTest{
    @Override
    String getCommandName() {
        return CommandName.NO.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return NoCommand.NO_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new NoCommand(sendMessageService);
    }
}