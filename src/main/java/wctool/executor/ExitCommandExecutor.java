package wctool.executor;

import wctool.io.Printer;
import wctool.models.Command;

public class ExitCommandExecutor implements CommandExecutor {

    public static final String EXIT_COMMAND = "exit";
    private Printer printer;

    public ExitCommandExecutor(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void execute(Command command) {
        printer.print("Shutting down server !!!");
        System.exit(0);
    }
}
