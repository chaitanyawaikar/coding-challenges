package wctool;

import wctool.factory.CommandExecutorFactory;
import wctool.factory.CommandValidatorFactory;
import wctool.io.ConsolePrinter;
import wctool.io.Printer;
import wctool.models.Command;
import wctool.service.CommandService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        start();
    }

    private static void start() throws IOException {
        Printer printer = new ConsolePrinter();
        printer.print("Starting the server. Waiting for user input");

        CommandValidatorFactory commandValidatorFactory = new CommandValidatorFactory();
        CommandExecutorFactory commandExecutorFactory = new CommandExecutorFactory(printer);
        CommandService service = new CommandService(commandValidatorFactory, commandExecutorFactory);

        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            final String input = reader.readLine();
            final Command command = new Command(input);
            service.processCommand(command);
        }
    }
}
