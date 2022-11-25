package ru.worldmac.wmbot.command.commands;

import ru.worldmac.wmbot.command.Command;
import ru.worldmac.wmbot.command.enums.CommandName;

public class StatCommandTest extends AbstractCommandTest{
    @Override
    String getCommandName() {
        return CommandName.START.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return String.format(StatCommand.STAT_MESSAGE, 0);
    }

    @Override
    Command getCommand() {
        return new StatCommand(sendMessageService, telegramUserService);
    }
}
