package wctool.models;

import wctool.exception.InvalidNumberOfArgumentsException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static wctool.util.Constants.WHITESPACE_SEPARATOR;

public class Command {

    private String commandName;
    private List<String> params;

    public Command(final String inputLine) {
        final List<String> tokensList =
                Arrays.stream(inputLine.trim().split(WHITESPACE_SEPARATOR))
                        .map(String::trim)
                        .filter(token -> (token.length() > 0)).collect(Collectors.toList());

        if (tokensList.size() == 0) {
            throw new InvalidNumberOfArgumentsException("Got 0 arguments");
        }

        commandName = tokensList.get(0).toLowerCase();
        tokensList.remove(0);
        params = tokensList;
    }

    public String getCommandName() {
        return commandName;
    }

    public List<String> getParams() {
        return params;
    }
}
