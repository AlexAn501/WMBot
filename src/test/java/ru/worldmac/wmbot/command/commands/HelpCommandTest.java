package ru.worldmac.wmbot.command.commands;

import org.junit.jupiter.api.DisplayName;
import ru.worldmac.wmbot.command.Command;
import ru.worldmac.wmbot.command.enums.CommandName;

@DisplayName("Unit-level testing for HelpCommand")
public class HelpCommandTest extends AbstractCommandTest{
    @Override
    String getCommandName() {
        return CommandName.HELP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return HelpCommand.HELP_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new HelpCommand(sendMessageService);
    }
}
