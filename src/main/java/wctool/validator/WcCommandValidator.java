package wctool.validator;

import wctool.exception.FileDoesNotExistException;
import wctool.exception.InvalidCommandOptionException;
import wctool.exception.InvalidNumberOfArgumentsException;
import wctool.models.Command;

import java.util.Set;

import static wctool.util.CollectionUtils.newHashSet;
import static wctool.util.Constants.BASE_PATH;
import static wctool.util.FileUtils.checkIfFileExists;

public class WcCommandValidator implements CommandValidator {

    private final Set<String> allowedCommandOptions = newHashSet("-c", "-l", "-w", "-m");

    @Override
    public boolean validate(Command command) {
        // The default option
        if (command.getParams().size() == 1) {
            validateCommandArgument(command);
        } else if (command.getParams().size() == 2) {
            validateCommandArgumentAndCommandOptions(command);
        } else {
            throw new InvalidNumberOfArgumentsException(
                    String.format("Expected 1 or 2 arguments. Got %s arguments", command.getParams().size())
            );
        }
        return true;
    }

    private static void validateCommandArgument(Command command) {
        String commandArgument = command.getParams().get(0);
        validateCommandArgument(commandArgument);
    }

    private void validateCommandArgumentAndCommandOptions(Command command) {
        String commandOption = command.getParams().get(0);
        String commandArgument = command.getParams().get(1);
        if (!allowedCommandOptions.contains(commandOption))
            throw new InvalidCommandOptionException(
                    String.format("The following command option %s is not valid. Allowed options are %s", commandOption, allowedCommandOptions)
            );
        validateCommandArgument(commandArgument);
    }

    private static void validateCommandArgument(String file) {
        String filePath = BASE_PATH + file;
        if (!checkIfFileExists(filePath))
            throw new FileDoesNotExistException(
                    String.format("The file %s does not exist in the path", file)
            );
    }
}
