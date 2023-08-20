package wctool.util;

import java.io.File;

public class FileUtils {

    public static boolean isFileExtension(String commandArgument) {
        String extension = commandArgument.substring(commandArgument.lastIndexOf(".") + 1);
        return extension.isEmpty();
    }

    public static boolean checkIfFileExists(String commandArgument) {
        return new File(commandArgument).exists();
    }
}
