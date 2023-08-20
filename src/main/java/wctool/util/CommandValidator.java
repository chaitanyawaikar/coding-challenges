package wctool.util;

import wctool.exception.FileDoesNotExistException;
import wctool.exception.InvalidCommandOptionException;
import wctool.exception.InvalidNumberOfArgumentsException;
import wctool.factory.CommandFactory;
import wctool.models.CustomCommand;

import static wctool.util.FileUtils.*;

public class CommandValidator {

    public boolean validateUserInput(String userInput) {
        String[] split = userInput.split(Constants.WHITESPACE_SEPARATOR);

        CustomCommand customCommand = CommandFactory.getCommand(split[0]);
        validateCommandStructure(split);
        validateCommandOptions(customCommand, split);
        validateCommandArgument(split);
        return true;
    }

    private static void validateCommandStructure(String[] split) {
        if (split.length < 3) {
            throw new InvalidNumberOfArgumentsException(
                    String.format("Expected 3 arguments. Got %s arguments", split.length)
            );
        }
    }

    private static void validateCommandOptions(CustomCommand customCommand, String[] split) {
        String commandOption = split[1].replaceFirst("-", "");
        if (!customCommand.getCommandOptions().contains(commandOption)) {
            throw new InvalidCommandOptionException(
                    String.format(
                            "Invalid customCommand option %s. Possible options are %s",
                            commandOption,
                            String.join(",", customCommand.getCommandOptions())
                    )
            );
        }
    }

    private void validateCommandArgument(String[] split) {
        String commandArgument = split[2];
        if (isFileExtension(commandArgument)) {
            if (checkIfFileExists(commandArgument))
                throw new FileDoesNotExistException(
                        String.format("The file %s does not exist in the path", commandArgument)
                );
        }
    }

    public static void main(String[] args) {

    }
}
