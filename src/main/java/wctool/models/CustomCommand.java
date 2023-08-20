package wctool.models;

import java.util.List;

public interface CustomCommand {

    String getCommandName();

    List<String> getCommandOptions();

    String getCommandArguments(String fileNameOrContents);
}
