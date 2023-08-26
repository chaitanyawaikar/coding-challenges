package wctool.factory;

import wctool.exception.UnknownCommandException;
import wctool.executor.CommandExecutor;
import wctool.executor.ExitCommandExecutor;
import wctool.executor.WCCommandExecutor;
import wctool.io.Printer;
import wctool.models.Command;

import java.util.HashMap;
import java.util.Optional;

import static wctool.executor.ExitCommandExecutor.EXIT_COMMAND;
import static wctool.executor.WCCommandExecutor.WC_COMMAND;

public class CommandExecutorFactory {

    private HashMap<String, CommandExecutor> commandExecutorMap;

    public CommandExecutorFactory(Printer printer) {
        commandExecutorMap = new HashMap<>();
        commandExecutorMap.put(WC_COMMAND, new WCCommandExecutor(printer));
        commandExecutorMap.put(EXIT_COMMAND, new ExitCommandExecutor(printer));
    }

    public CommandExecutor getCommandExecutor(Command command) {
        CommandExecutor commandExecutor = commandExecutorMap.get(command.getCommandName());
        if (Optional.ofNullable(commandExecutor).isEmpty())
            throw new UnknownCommandException(String.format("Unknown command: %s", command.getCommandName()));
        else return commandExecutor;
    }
}
