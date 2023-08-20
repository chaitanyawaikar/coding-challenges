package wctool.exception;

public class FileDoesNotExistException extends BusinessException {
    public FileDoesNotExistException() {
    }

    public FileDoesNotExistException(String message) {
        super(message);
    }
}
