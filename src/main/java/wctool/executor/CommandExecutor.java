package wctool.executor;

import wctool.util.CommandValidator;

public class CommandExecutor {

    private CommandValidator validator;

    public CommandExecutor(CommandValidator validator) {
        this.validator = validator;
    }

    public void execute(String userInput) {
        if (validator.validateUserInput(userInput)) {
//            CommandFactory.getCommand()
        }
    }
}
