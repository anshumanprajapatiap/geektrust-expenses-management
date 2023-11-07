package com.anshumanprajapati.geektrustExpenseManagement.converter;

import com.anshumanprajapati.geektrustExpenseManagement.converter.CommandConverter;
import com.anshumanprajapati.geektrustExpenseManagement.enums.OperationEnum;
import com.anshumanprajapati.geektrustExpenseManagement.model.CommandModel;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static com.anshumanprajapati.geektrustExpenseManagement.constant.ExpenseManagementConstants.*;

public class CommandConverterTest {

    @Test
    public void testGetCommandFromListWithValidData() {
        CommandConverter converter = new CommandConverter();

        List<String> commandList = Arrays.asList("SPEND", "100", "Alice", "Bob");

        try {
            CommandModel command = converter.getCommandFromList(commandList);

            assertNotNull(command);
            assertEquals(OperationEnum.SPEND, command.getInputCommand());
            assertEquals(3, command.getCommandParams().size());
            assertEquals("100", command.getCommandParams().get(0));
            assertEquals("Alice", command.getCommandParams().get(1));
            assertEquals("Bob", command.getCommandParams().get(2));
        } catch (Exception e) {
            fail("Exception should not be thrown for valid data: " + e.getMessage());
        }
    }

    @Test
    public void testGetCommandFromListWithInvalidData() {
        CommandConverter converter = new CommandConverter();

        List<String> invalidCommandList = Arrays.asList("INVALID_COMMAND", "100", "Alice", "Bob");

        assertThrows(Exception.class, () -> converter.getCommandFromList(invalidCommandList));
    }

    @Test
    public void testGetCommandFromStringWithValidData() {
        CommandConverter converter = new CommandConverter();

        String validCommandString = "SPEND 100 Alice Bob";

        try {
            CommandModel command = converter.getCommandFromString(validCommandString);

            assertNotNull(command);
            assertEquals(OperationEnum.SPEND, command.getInputCommand());
            assertEquals(3, command.getCommandParams().size());
            assertEquals("100", command.getCommandParams().get(0));
            assertEquals("Alice", command.getCommandParams().get(1));
            assertEquals("Bob", command.getCommandParams().get(2));
        } catch (Exception e) {
            fail("Exception should not be thrown for valid data: " + e.getMessage());
        }
    }

    @Test
    public void testGetCommandFromStringWithInvalidData() {
        CommandConverter converter = new CommandConverter();

        String invalidCommandString = "INVALID_COMMAND 100 Alice Bob";

        assertThrows(Exception.class, () -> converter.getCommandFromString(invalidCommandString));
    }
}
