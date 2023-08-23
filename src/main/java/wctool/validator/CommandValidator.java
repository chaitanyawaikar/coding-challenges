package wctool.validator;

import wctool.models.Command;

public interface CommandValidator {

    boolean validate(Command command);
}
