package wctool.factory;

import wctool.exception.UnknownCommandException;
import wctool.models.CustomCommand;
import wctool.models.WcCustomCommand;

import java.util.List;

public class CommandFactory {

    public static List<CustomCommand> supportedCommands() {
        return List.of(
                WcCustomCommand.of()
        );
    }

    public static CustomCommand getCommand(String cmd) {
        return supportedCommands()
                .stream()
                .filter(customCommand -> customCommand.getCommandOptions().contains(cmd))
                .findFirst()
                .orElseThrow(() -> new UnknownCommandException(String.format("Unknown command: %s", cmd)));
    }
}
