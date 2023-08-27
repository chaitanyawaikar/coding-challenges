package wctool.mode;

import wctool.models.Command;
import wctool.service.CommandService;

public class SingleCommandMode implements Mode {

    private CommandService service;

    public SingleCommandMode(CommandService service) {
        this.service = service;
    }

    @Override
    public void process(String userInput) {
        final Command command = new Command(userInput);
        service.processCommand(command);
    }
}
