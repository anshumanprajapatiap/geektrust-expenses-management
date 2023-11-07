package com.anshumanprajapati.geektrustExpenseManagement.model;

import com.anshumanprajapati.geektrustExpenseManagement.enums.OperationEnum;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CommandModelTest {

    @Test
    public void testCommandModelInitialization() {
        List<String> commandParams = new ArrayList<>();
        commandParams.add("param1");
        commandParams.add("param2");
        OperationEnum inputCommand = OperationEnum.MOVE_IN;

        CommandModel commandModel = new CommandModel(inputCommand, commandParams);

        assertNotNull(commandModel);
        assertEquals(inputCommand, commandModel.getInputCommand());
        assertEquals(commandParams, commandModel.getCommandParams());
    }
}
