package wctool.exception;

public class InvalidNumberOfArgumentsException extends BusinessException {
    public InvalidNumberOfArgumentsException() {
    }

    public InvalidNumberOfArgumentsException(String message) {
        super(message);
    }
}
