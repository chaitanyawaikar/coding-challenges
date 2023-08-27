package wctool.mode;

import wctool.exception.UnknownCommandException;
import wctool.models.Command;
import wctool.service.CommandService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static wctool.util.Constants.*;

public class PipeCommandMode implements Mode {

    private CommandService service;

    public PipeCommandMode(CommandService service) {
        this.service = service;
    }

    @Override
    public void process(String userInput) throws IOException {
        final List<String> listOfCommands = Arrays.stream(userInput.split(PIPE_SEPARATOR_REGEX))
                .map(String::trim)
                .toList();

        final List<String> lines = executeShellCommand(listOfCommands);
        storeResultToTempFile(lines);

        String inputCommand = listOfCommands.get(1) + WHITESPACE_SEPARATOR + TEMP_FILE;
        final Command command = new Command(inputCommand);
        service.processCommand(command);
    }

    private void storeResultToTempFile(List<String> lines) throws IOException {
        Path file = Paths.get(BASE_PATH + TEMP_FILE);
        Files.write(file, lines, StandardCharsets.UTF_8);
    }

    private List<String> executeShellCommand(List<String> shellCommands) throws UnknownCommandException {
        // TODO :- Remove the hardcoding and modify the function to parse multiple commands dynamically
        String firstCommand = shellCommands.get(0);
        List<String> lines = new LinkedList<>();
        try {
            Process process = Runtime.getRuntime().exec(firstCommand);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (Exception e) {
            throw new UnknownCommandException(String.format("Incorrect shell command: %s", firstCommand));
        }
        return lines;
    }
}
