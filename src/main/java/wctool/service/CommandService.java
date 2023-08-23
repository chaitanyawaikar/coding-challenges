package wctool.service;

import wctool.factory.CommandExecutorFactory;
import wctool.factory.CommandValidatorFactory;
import wctool.io.Printer;
import wctool.models.Command;

public class CommandService {

    private CommandValidatorFactory commandValidatorFactory;
    private CommandExecutorFactory commandExecutorFactory;

    public CommandService(Printer printer) {
        this.commandValidatorFactory = new CommandValidatorFactory();
        this.commandExecutorFactory = new CommandExecutorFactory(printer);
    }

    public void processCommand(Command command) {
        boolean isCommandValid = commandValidatorFactory
                .getCommandValidator(command)
                .validate(command);

        if (isCommandValid) {
            commandExecutorFactory
                    .getCommandExecutor(command)
                    .execute(command);
        }
    }
}
