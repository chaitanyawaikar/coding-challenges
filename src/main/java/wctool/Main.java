package wctool;

import wctool.factory.CommandExecutorFactory;
import wctool.factory.CommandValidatorFactory;
import wctool.io.ConsolePrinter;
import wctool.io.Printer;
import wctool.mode.PipeCommandMode;
import wctool.mode.SingleCommandMode;
import wctool.service.CommandService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        start();
    }

    private static void start() throws IOException {
        CommandService service = setupCommandService();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            final String input = reader.readLine();
            if (input.contains("|")) new PipeCommandMode(service).process(input);
            else new SingleCommandMode(service).process(input);
        }
    }

    private static CommandService setupCommandService() {
        Printer printer = new ConsolePrinter();
        printer.print("Starting the server. Waiting for user input");

        CommandValidatorFactory commandValidatorFactory = new CommandValidatorFactory();
        CommandExecutorFactory commandExecutorFactory = new CommandExecutorFactory(printer);
        return new CommandService(commandValidatorFactory, commandExecutorFactory);
    }
}
