package wctool.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wctool.exception.UnknownCommandException;
import wctool.models.Command;
import wctool.validator.CommandValidator;
import wctool.validator.ExitCommandValidator;
import wctool.validator.WcCommandValidator;

import static org.junit.jupiter.api.Assertions.*;
import static wctool.executor.WCCommandExecutor.WC_COMMAND;

class CommandValidatorFactoryTest {

    private CommandValidatorFactory commandValidatorFactory;

    @BeforeEach
    public void setUp() {
        commandValidatorFactory = new CommandValidatorFactory();
    }

    @Test
    public void shouldGetWCCommandExecutorExecutorForccwcCommand() {
        CommandValidator validator = commandValidatorFactory.getCommandValidator(new Command(WC_COMMAND));
        assertNotNull(validator);
        assertTrue(validator instanceof WcCommandValidator);
    }

    @Test
    public void shouldGetExitCommandExecutorExecutorForExitCommand() {
        CommandValidator validator = commandValidatorFactory.getCommandValidator(new Command("exit"));
        assertNotNull(validator);
        assertTrue(validator instanceof ExitCommandValidator);
    }

    @Test
    public void shouldThrowUnknownCommandExceptionForInvalidCommand() {
        assertThrows(UnknownCommandException.class,
                () -> commandValidatorFactory.getCommandValidator(new Command("unknown")));
    }
}