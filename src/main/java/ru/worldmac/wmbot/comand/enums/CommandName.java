package ru.worldmac.wmbot.comand.enums;


/**
 * Enumeration for Command's.
 */

public enum CommandName {
    START("/start"),
    STOP("/stop"),
    LIST("/list"),
    HELP("/help"),
    STAT("/stat"),
    ADD_GROUP_SUB("/addgroup"),
    NO("nocommand");

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    private final String commandName;

    public String getCommandName() {
        return commandName;
    }
}
