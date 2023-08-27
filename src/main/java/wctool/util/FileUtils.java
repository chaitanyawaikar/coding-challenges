package wctool.util;

import java.io.File;

public class FileUtils {

    public static boolean checkIfFileExists(String commandArgument) {
        return new File(commandArgument).exists();
    }
}
