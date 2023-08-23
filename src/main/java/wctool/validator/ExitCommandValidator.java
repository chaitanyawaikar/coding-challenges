package wctool.validator;

import wctool.models.Command;

public class ExitCommandValidator implements wctool.validator.CommandValidator {
    @Override
    public boolean validate(Command command) {
        return command.getParams().isEmpty();
    }
}
