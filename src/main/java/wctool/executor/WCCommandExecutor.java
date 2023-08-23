package wctool.executor;

import wctool.io.Printer;
import wctool.models.Command;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringTokenizer;

import static wctool.util.Constants.BASE_PATH;
import static wctool.util.Constants.WHITESPACE_SEPARATOR;

public class WCCommandExecutor implements CommandExecutor {

    private Printer printer;

    public WCCommandExecutor(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void execute(Command command) {
        String commandOption;
        String fileName;
        if (command.getParams().size() == 2) {
            commandOption = command.getParams().get(0);
            fileName = BASE_PATH + command.getParams().get(1);
            computeResult(commandOption, fileName);
        } else {
            commandOption = "";
            fileName = BASE_PATH + command.getParams().get(0);
            computeResult(commandOption, fileName);
        }
    }

    private void computeResult(String commandOption, String fileName) {
        switch (commandOption) {
            case "-c" -> calculateBytes(fileName);
            case "-l" -> calculateNumberOfLines(fileName);
            case "-w" -> calculateNumberOfWords(fileName);
            case "-m" -> calculateNumberOfCharacters(fileName);
            default -> calculateAllActions(fileName);
        }
    }

    private void calculateBytes(String fileName) {
        Path path = Paths.get(fileName);
        try {
            // size of a file (in bytes)
            long bytes = Files.size(path);
            printer.print("Bytes: " + bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void calculateNumberOfLines(String fileName) {
        Path path = Paths.get(fileName);
        try {
            long lines = Files.lines(path).count();
            printer.print("Lines: " + lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void calculateNumberOfWords(String fileName) {
        Path path = Paths.get(fileName);
        try {
            long words =
                    Files
                            .lines(path)
                            .mapToInt(str -> new StringTokenizer(str).countTokens())
                            .reduce(0, Integer::sum);
            printer.print("Words: " + words);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void calculateNumberOfCharacters(String fileName) {
        Path path = Paths.get(fileName);
        try {
            long characters =
                    Files
                            .readAllLines(path)
                            .stream()
                            .mapToLong(str -> str.chars().count())
                            .sum();
            printer.print("Characters: " + characters);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void calculateAllActions(String fileName) {
        calculateBytes(fileName);
        calculateNumberOfLines(fileName);
        calculateNumberOfWords(fileName);
    }
}
