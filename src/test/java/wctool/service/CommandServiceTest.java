package wctool.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wctool.executor.CommandExecutor;
import wctool.factory.CommandExecutorFactory;
import wctool.factory.CommandValidatorFactory;
import wctool.models.Command;
import wctool.validator.CommandValidator;

import static org.mockito.Mockito.*;

class CommandServiceTest {

    private CommandService commandService;
    private CommandValidatorFactory commandValidatorFactoryMock;
    private CommandExecutorFactory commandExecutorFactoryMock;

    @BeforeEach
    void setup() {
        commandValidatorFactoryMock = mock(CommandValidatorFactory.class);
        commandExecutorFactoryMock = mock(CommandExecutorFactory.class);
        commandService = new CommandService(commandValidatorFactoryMock, commandExecutorFactoryMock);
    }

    @Test
    void shouldProcessCommandForValidCommand() {
        // Given
        Command command = new Command("validCommand");
        CommandValidator validatorMock = mock(CommandValidator.class);
        CommandExecutor commandExecutorMock = mock(CommandExecutor.class);

        when(commandValidatorFactoryMock.getCommandValidator(command)).thenReturn(validatorMock);
        when(validatorMock.validate(command)).thenReturn(true);
        when(commandExecutorFactoryMock.getCommandExecutor(command)).thenReturn(commandExecutorMock);

        // When
        commandService.processCommand(command);

        // Then
        verify(commandValidatorFactoryMock).getCommandValidator(command);
        verify(validatorMock).validate(command);
        verify(commandExecutorFactoryMock).getCommandExecutor(command);
        verify(commandExecutorMock).execute(command);
        verifyNoMoreInteractions(commandValidatorFactoryMock, commandExecutorFactoryMock);
    }

    @Test
    void shouldNotProcessCommandForInvalidCommand() {
        // Given
        Command command = new Command("invalidCommand");
        CommandValidator validatorMock = mock(CommandValidator.class);

        when(commandValidatorFactoryMock.getCommandValidator(command)).thenReturn(validatorMock);
        when(validatorMock.validate(command)).thenReturn(false);

        // When
        commandService.processCommand(command);

        // Then
        verify(commandValidatorFactoryMock).getCommandValidator(command);
        verify(validatorMock).validate(command);
        verifyNoMoreInteractions(commandValidatorFactoryMock, commandExecutorFactoryMock);
    }
}