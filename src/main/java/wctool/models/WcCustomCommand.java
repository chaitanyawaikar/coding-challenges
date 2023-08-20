package wctool.models;

import java.util.List;

public class WcCustomCommand implements CustomCommand {

    private static final String COMMAND = "wc";

    public static WcCustomCommand of() {
        return new WcCustomCommand();
    }

    public String getCommandName() {
        return COMMAND;
    }

    public List<String> getCommandOptions() {
        return List.of(
                "c",
                "w",
                "l"
        );
    }

    public String getCommandArguments(String fileNameOrContents) {
        return null;
    }
}
