package ru.worldmac.wmbot.comand;

import com.google.common.collect.ImmutableMap;
import ru.worldmac.wmbot.comand.commands.AddGroupSubCommand;
import ru.worldmac.wmbot.comand.commands.HelpCommand;
import ru.worldmac.wmbot.comand.commands.ListCommand;
import ru.worldmac.wmbot.comand.commands.NoCommand;
import ru.worldmac.wmbot.comand.commands.StartCommand;
import ru.worldmac.wmbot.comand.commands.StatCommand;
import ru.worldmac.wmbot.comand.commands.StopCommand;
import ru.worldmac.wmbot.comand.commands.UnknownCommand;
import ru.worldmac.wmbot.comand.enums.CommandName;
import ru.worldmac.wmbot.feign.JRGroupClient;
import ru.worldmac.wmbot.feign.JRPostsClient;
import ru.worldmac.wmbot.service.GroupSubService;
import ru.worldmac.wmbot.service.SendMessageService;
import ru.worldmac.wmbot.service.TelegramUserService;

/**
 * Container of the {@link Command}s, which are using for handling telegram commands.
 */
public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
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
                .build();

        unknownCommand = new UnknownCommand(sendMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }

}
