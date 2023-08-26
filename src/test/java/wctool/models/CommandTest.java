package wctool.models;

import org.junit.jupiter.api.Test;
import wctool.exception.InvalidNumberOfArgumentsException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandTest {

    @Test
    public void shouldCreateCommandForValidInput() {
        //given
        String inputLine = "CommandName param1 param2 param3";

        //when
        Command command = new Command(inputLine);

        //then
        List<String> params = command.getParams();
        assertEquals("commandname", command.getCommandName());
        assertEquals(3, params.size());
        assertEquals("param1", params.get(0));
        assertEquals("param2", params.get(1));
        assertEquals("param3", params.get(2));
    }

    @Test
    public void shouldCreateCommandWithNoParams() {
        // given
        String inputLine = "CommandName";

        // when
        Command command = new Command(inputLine);

        // then
        List<String> params = command.getParams();
        assertEquals("commandname", command.getCommandName());
        assertEquals(0, params.size());
    }

    @Test
    public void shouldCreateCommandEvenWithMultipleSpaces() {
        // given
        String inputLine = "  CommandName   param1   param2   ";

        // when
        Command command = new Command(inputLine);

        // then
        List<String> params = command.getParams();
        assertEquals("commandname", command.getCommandName());
        assertEquals(2, params.size());
        assertEquals("param1", params.get(0));
        assertEquals("param2", params.get(1));
    }

    @Test
    public void shouldThrowInvalidNumberOfArgumentsExceptionWhenInputCommandIsEmpty() {
        assertThrows(InvalidNumberOfArgumentsException.class, () -> {
            new Command("");
        });
    }

    @Test
    public void shouldThrowInvalidNumberOfArgumentsExceptionWhenInputCommandHasOnlySpaces() {
        assertThrows(InvalidNumberOfArgumentsException.class, () -> {
            new Command("   ");
        });
    }
}
