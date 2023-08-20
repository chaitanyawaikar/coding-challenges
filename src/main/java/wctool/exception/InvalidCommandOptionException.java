package wctool.exception;

public class InvalidCommandOptionException extends BusinessException {

    public InvalidCommandOptionException() {
    }

    public InvalidCommandOptionException(String message) {
        super(message);
    }
}
