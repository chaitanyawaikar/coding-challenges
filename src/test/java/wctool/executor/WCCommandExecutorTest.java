package wctool.executor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wctool.exception.InvalidCommandOptionException;
import wctool.io.Printer;
import wctool.models.Command;

import static org.mockito.Mockito.*;

class WCCommandExecutorTest {

    private WCCommandExecutor wcCommandExecutor;
    private Printer mockPrinter;

    @BeforeEach
    public void setUp() {
        mockPrinter = mock(Printer.class);
        wcCommandExecutor = new WCCommandExecutor(mockPrinter);
    }

    @Test
    public void shouldPrintAllOptionsWhenNoCommandOptionProvided() {
        //Given
        String inputLine = "ccwc test.txt";
        Command command = new Command(inputLine);

        // When
        wcCommandExecutor.execute(command);

        // Then
        verify(mockPrinter).print("Bytes: 335182");
        verify(mockPrinter).print("Lines: 7188");
        verify(mockPrinter).print("Words: 58163");
        verifyNoMoreInteractions(mockPrinter);
    }

    @Test
    public void shouldCalculateBytesOfInputFileForCCommandOption() {
        //Given
        String inputLine = "ccwc -c test.txt";
        Command command = new Command(inputLine);

        // When
        wcCommandExecutor.execute(command);

        // Then
        verify(mockPrinter).print("Bytes: 335182");
        verifyNoMoreInteractions(mockPrinter);
    }

    @Test
    public void shouldCalculateNumberOfLinesOfInputFileForLCommandOption() {
        // Given
        String inputLine = "ccwc -l test.txt";
        Command command = new Command(inputLine);

        // When
        wcCommandExecutor.execute(command);

        // Then
        verify(mockPrinter).print("Lines: 7188");
        verifyNoMoreInteractions(mockPrinter);
    }

    @Test
    public void shouldCalculateNumberOfWordsOfInputFileForWCommandOption() {
        // Given
        String inputLine = "ccwc -w test.txt";
        Command command = new Command(inputLine);

        // When
        wcCommandExecutor.execute(command);

        // Then
        verify(mockPrinter).print("Words: 58163");
        verifyNoMoreInteractions(mockPrinter);
    }

    @Test
    public void shouldCalculateNumberOfCharactersOfInputFileForMCommandOption() {
        // Given
        String inputLine = "ccwc -m test.txt";
        Command command = new Command(inputLine);

        // When
        wcCommandExecutor.execute(command);

        // Then
        verify(mockPrinter).print("Characters: 325098");
        verifyNoMoreInteractions(mockPrinter);
    }

    @Test
    public void shouldThrowInvalidCommandOptionExceptionForInvalidOption() {
        // Given
        String inputLine = "ccwc -x test.txt";
        Command command = new Command(inputLine);

        // When and Then
        Assertions.assertThrows(InvalidCommandOptionException.class,
                () -> wcCommandExecutor.execute(command));
    }
}