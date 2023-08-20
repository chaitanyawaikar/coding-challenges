package wctool.exception;

public class UnknownCommandException extends BusinessException {

    public UnknownCommandException() {
    }

    public UnknownCommandException(String command) {
        super(command);
    }
}
