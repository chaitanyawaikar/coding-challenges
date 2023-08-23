package wctool.executor;

import wctool.models.Command;

public class ExitCommandExecutor implements CommandExecutor {

    public static final String EXIT_COMMAND = "exit";

    @Override
    public void execute(Command command) {
        System.exit(0);
    }
}
