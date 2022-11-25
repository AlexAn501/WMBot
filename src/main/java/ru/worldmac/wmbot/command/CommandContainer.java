package ru.worldmac.wmbot.command;

import com.google.common.collect.ImmutableMap;
import ru.worldmac.wmbot.command.commands.AddGroupSubCommand;
import ru.worldmac.wmbot.command.commands.HelpCommand;
import ru.worldmac.wmbot.command.commands.ListCommand;
import ru.worldmac.wmbot.command.commands.ListGroupSubCommand;
import ru.worldmac.wmbot.command.commands.NoCommand;
import ru.worldmac.wmbot.command.commands.StartCommand;
import ru.worldmac.wmbot.command.commands.StatCommand;
import ru.worldmac.wmbot.command.commands.StopCommand;
import ru.worldmac.wmbot.command.commands.UnknownCommand;
import ru.worldmac.wmbot.command.enums.CommandName;
import ru.worldmac.wmbot.feign.JRGroupClient;
import ru.worldmac.wmbot.service.GroupSubService;
import ru.worldmac.wmbot.service.SendMessageService;
import ru.worldmac.wmbot.service.TelegramUserService;

import java.util.Map;

/**
 * Container of the {@link Command}s, which are using for handling telegram commands.
 */
public class CommandContainer {

    private final Map<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(SendMessageService sendMessageService, TelegramUserService telegramUserService,
                            GroupSubService groupSubService,
                            JRGroupClient jRGroupClient) {

        commandMap = ImmutableMap.<String, Command>builder()
                .put(CommandName.START.getCommandName(),
                        new StartCommand(sendMessageService, telegramUserService))
                .put(CommandName.STOP.getCommandName(), new StopCommand(sendMessageService, telegramUserService))
                .put(CommandName.LIST.getCommandName(), new ListCommand(sendMessageService))
                .put(CommandName.HELP.getCommandName(), new HelpCommand(sendMessageService))
                .put(CommandName.NO.getCommandName(), new NoCommand(sendMessageService))
                .put(CommandName.STAT.getCommandName(), new StatCommand(sendMessageService, telegramUserService))
                .put(CommandName.ADD_GROUP_SUB.getCommandName(),
                        new AddGroupSubCommand(sendMessageService, jRGroupClient, groupSubService))
                .put(CommandName.LIST_GROUP_SUB.getCommandName(),
                        new ListGroupSubCommand(sendMessageService, telegramUserService))
                .build();

        unknownCommand = new UnknownCommand(sendMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }

}
