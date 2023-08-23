package wctool.factory;

import wctool.exception.UnknownCommandException;
import wctool.models.Command;
import wctool.validator.CommandValidator;
import wctool.validator.ExitCommandValidator;
import wctool.validator.WcCommandValidator;

import java.util.HashMap;
import java.util.Optional;

import static wctool.executor.ExitCommandExecutor.EXIT_COMMAND;
import static wctool.executor.WCCommandExecutor.WC_COMMAND;

public class CommandValidatorFactory {

    private HashMap<String, CommandValidator> commandValidatorMap;

    public CommandValidatorFactory() {
        commandValidatorMap = new HashMap<>();
        commandValidatorMap.put(WC_COMMAND, new WcCommandValidator());
        commandValidatorMap.put(EXIT_COMMAND, new ExitCommandValidator());
    }

    public CommandValidator getCommandValidator(Command command) {
        CommandValidator commandValidator = commandValidatorMap.get(command.getCommandName());
        if (Optional.ofNullable(commandValidator).isEmpty())
            throw new UnknownCommandException(String.format("Unknown command: %s", command.getCommandName()));
        else return commandValidator;
    }
}
