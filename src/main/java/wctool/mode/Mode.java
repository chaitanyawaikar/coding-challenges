package wctool.mode;

import java.io.IOException;

public interface Mode {

    void process(String userInput) throws IOException;
}
