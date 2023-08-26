package wctool.service;

import wctool.factory.CommandExecutorFactory;
import wctool.factory.CommandValidatorFactory;
import wctool.models.Command;

public class CommandService {

    private CommandValidatorFactory commandValidatorFactory;
    private CommandExecutorFactory commandExecutorFactory;

    public CommandService(
            CommandValidatorFactory commandValidatorFactory,
            CommandExecutorFactory commandExecutorFactory
    ) {
        this.commandValidatorFactory = commandValidatorFactory;
        this.commandExecutorFactory = commandExecutorFactory;
    }

    public void processCommand(Command command) {
        boolean isCommandValid = commandValidatorFactory.getCommandValidator(command).validate(command);

        if (isCommandValid) {
            commandExecutorFactory.getCommandExecutor(command).execute(command);
        }
    }
}
