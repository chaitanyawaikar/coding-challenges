package wctool.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import wctool.exception.InvalidNumberOfArgumentsException;

import java.util.List;

public class CommandTest {

    @Test
    public void shouldCreateCommandForValidInput() {
        //given
        String inputLine = "CommandName param1 param2 param3";

        //when
        Command command = new Command(inputLine);

        //then
        List<String> params = command.getParams();
        Assertions.assertEquals("commandname", command.getCommandName());
        Assertions.assertEquals(3, params.size());
        Assertions.assertEquals("param1", params.get(0));
        Assertions.assertEquals("param2", params.get(1));
        Assertions.assertEquals("param3", params.get(2));
    }

    @Test
    public void shouldCreateCommandWithNoParams() {
        // given
        String inputLine = "CommandName";

        // when
        Command command = new Command(inputLine);

        // then
        List<String> params = command.getParams();
        Assertions.assertEquals("commandname", command.getCommandName());
        Assertions.assertEquals(0, params.size());
    }

    @Test
    public void shouldCreateCommandEvenWithMultipleSpaces() {
        // given
        String inputLine = "  CommandName   param1   param2   ";

        // when
        Command command = new Command(inputLine);

        // then
        List<String> params = command.getParams();
        Assertions.assertEquals("commandname", command.getCommandName());
        Assertions.assertEquals(2, params.size());
        Assertions.assertEquals("param1", params.get(0));
        Assertions.assertEquals("param2", params.get(1));
    }

    @Test
    public void shouldThrowInvalidNumberOfArgumentsExceptionWhenInputCommandIsEmpty() {
        Assertions.assertThrows(InvalidNumberOfArgumentsException.class, () -> {
            new Command("");
        });
    }

    @Test
    public void shouldThrowInvalidNumberOfArgumentsExceptionWhenInputCommandHasOnlySpaces() {
        Assertions.assertThrows(InvalidNumberOfArgumentsException.class, () -> {
            new Command("   ");
        });
    }
}
