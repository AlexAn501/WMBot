package ru.worldmac.wmbot.command.commands;

import org.junit.jupiter.api.DisplayName;
import ru.worldmac.wmbot.command.Command;
import ru.worldmac.wmbot.command.enums.CommandName;

@DisplayName("Unit-level testing for ListCommand")
public class ListCommandTest extends AbstractCommandTest{
    @Override
    String getCommandName() {
        return CommandName.LIST.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return ListCommand.LIST_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new ListCommand(sendMessageService);
    }
}