package ru.worldmac.wmbot.command.enums;


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
    LIST_GROUP_SUB("/listgroup"),
    NO("nocommand");

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    private final String commandName;

    public String getCommandName() {
        return commandName;
    }
}
