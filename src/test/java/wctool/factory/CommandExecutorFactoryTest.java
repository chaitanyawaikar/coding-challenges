package wctool.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import wctool.exception.UnknownCommandException;
import wctool.executor.CommandExecutor;
import wctool.executor.ExitCommandExecutor;
import wctool.executor.WCCommandExecutor;
import wctool.io.Printer;
import wctool.models.Command;

import static org.junit.jupiter.api.Assertions.*;
import static wctool.executor.WCCommandExecutor.WC_COMMAND;

class CommandExecutorFactoryTest {

    private CommandExecutorFactory commandExecutorFactory;
    private Printer mockPrinter;

    @BeforeEach
    public void setUp() {
        mockPrinter = Mockito.mock(Printer.class);
        commandExecutorFactory = new CommandExecutorFactory(mockPrinter);
    }

    @Test
    public void shouldReturnWCCommandExecutorExecutorForValidCommand() {
        CommandExecutor executor = commandExecutorFactory.getCommandExecutor(new Command(WC_COMMAND));
        assertNotNull(executor);
        assertTrue(executor instanceof WCCommandExecutor);
    }

    @Test
    public void shouldReturnExitCommandExecutorExecutorForExitCommand() {
        CommandExecutor executor = commandExecutorFactory.getCommandExecutor(new Command("exit"));
        assertNotNull(executor);
        assertTrue(executor instanceof ExitCommandExecutor);
    }

    @Test
    public void shouldThrowUnknownCommandExceptionForInvalidCommand() {
        assertThrows(UnknownCommandException.class,
                () -> commandExecutorFactory.getCommandExecutor(new Command("unknown")));
    }
}