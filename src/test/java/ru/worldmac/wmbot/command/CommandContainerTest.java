package ru.worldmac.wmbot.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.worldmac.wmbot.comand.Command;
import ru.worldmac.wmbot.comand.CommandContainer;
import ru.worldmac.wmbot.comand.commands.UnknownCommand;
import ru.worldmac.wmbot.comand.enums.CommandName;
import ru.worldmac.wmbot.feign.JRGroupClient;
import ru.worldmac.wmbot.feign.JRPostsClient;
import ru.worldmac.wmbot.service.SendMessageService;
import ru.worldmac.wmbot.service.TelegramUserService;

import java.util.Arrays;

@DisplayName("Unit-level testing for CommandContainer")
class CommandContainerTest {

    private CommandContainer commandContainer;

    @BeforeEach
    public void init() {
        SendMessageService sendBotMessageService = Mockito.mock(SendMessageService.class);
        TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);
        JRPostsClient jrPostsClient = Mockito.mock(JRPostsClient.class);
        JRGroupClient jrGroupClient = Mockito.mock(JRGroupClient.class);
        commandContainer = new CommandContainer(sendBotMessageService, telegramUserService, jrPostsClient, jrGroupClient);
    }

    @Test
    public void shouldGetAllTheExistingCommands() {
        //when-then
        Arrays.stream(CommandName.values())
                .forEach(commandName -> {
                    Command command = commandContainer.retrieveCommand(commandName.getCommandName());
                    Assertions.assertNotEquals(UnknownCommand.class, command.getClass());
                });
    }

    @Test
    public void shouldReturnUnknownCommand() {
        //given
        String unknownCommand = "/fgjhdfgdfg";

        //when
        Command command = commandContainer.retrieveCommand(unknownCommand);

        //then
        Assertions.assertEquals(UnknownCommand.class, command.getClass());
    }
}