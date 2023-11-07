package com.anshumanprajapati.geektrustExpenseManagement.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.anshumanprajapati.geektrustExpenseManagement.enums.OperationEnum;
import com.anshumanprajapati.geektrustExpenseManagement.model.CommandModel;

public class CommandValidationTest {

    @Test
    public void testValidateCommandValid() {
        CommandValidation commandValidation = new CommandValidation();
        CommandModel validCommand = new CommandModel(OperationEnum.MOVE_IN, Arrays.asList("Alice"));
        assertTrue(commandValidation.validateCommand(validCommand));
    }

    @Test
    public void testValidateCommandInvalidOperation() {
        CommandValidation commandValidation = new CommandValidation();
        CommandModel invalidCommand = new CommandModel(null, Arrays.asList("Alice"));
        assertFalse(commandValidation.validateCommand(invalidCommand));
    }

    @Test
    public void testValidateCommandInvalidInputParams() {
        CommandValidation commandValidation = new CommandValidation();
        CommandModel invalidCommand = new CommandModel(OperationEnum.MOVE_IN, Arrays.asList());
        assertFalse(commandValidation.validateCommand(invalidCommand));
    }

    @Test
    public void testValidateCommandValidWithMinMaxParams() {
        CommandValidation commandValidation = new CommandValidation();
        CommandModel validCommand = new CommandModel(OperationEnum.SPEND, Arrays.asList("50", "Alice", "Bob"));
        assertTrue(commandValidation.validateCommand(validCommand));
    }

    @Test
    public void testValidateCommandInvalidMinParams() {
        CommandValidation commandValidation = new CommandValidation();
        CommandModel invalidCommand = new CommandModel(OperationEnum.SPEND, Arrays.asList("Alice"));
        assertFalse(commandValidation.validateCommand(invalidCommand));
    }

    @Test
    public void testValidateCommandInvalidMaxParams() {
        CommandValidation commandValidation = new CommandValidation();
        CommandModel invalidCommand = new CommandModel(OperationEnum.SPEND, Arrays.asList("50", "Alice", "John", "Rat", "Extra", "Extra2"));
        assertFalse(commandValidation.validateCommand(invalidCommand));
    }
}
