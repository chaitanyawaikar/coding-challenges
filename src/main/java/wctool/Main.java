package wctool;

import wctool.io.ConsolePrinter;
import wctool.io.Printer;
import wctool.models.Command;
import wctool.service.CommandService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setProperty("file.encoding", "UTF-8");
        start();
    }

    private static void start() throws IOException {
        Printer printer = new ConsolePrinter();
        CommandService service = new CommandService(printer);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            final String input = reader.readLine();
            final Command command = new Command(input);
            service.processCommand(command);
            // TODO - Add exit command executor
        }
    }
}
