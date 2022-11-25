package ru.worldmac.wmbot.command.commands;

import org.junit.jupiter.api.DisplayName;
import ru.worldmac.wmbot.command.Command;

@DisplayName("Unit-level testing for UnknownCommand")
public class UnknownCommandTest extends AbstractCommandTest{
    @Override
    String getCommandName() {
        return "/dsfads";
    }

    @Override
    String getCommandMessage() {
        return UnknownCommand.UNKNOWN_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new UnknownCommand(sendMessageService);
    }
}