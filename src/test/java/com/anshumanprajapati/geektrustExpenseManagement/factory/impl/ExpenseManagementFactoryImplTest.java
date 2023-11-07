package com.anshumanprajapati.geektrustExpenseManagement.factory.impl;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.anshumanprajapati.geektrustExpenseManagement.enums.OperationEnum;
import com.anshumanprajapati.geektrustExpenseManagement.model.CommandModel;
import com.anshumanprajapati.geektrustExpenseManagement.service.ExpenseManagementService;
import com.anshumanprajapati.geektrustExpenseManagement.service.impl.MoveInServiceImpl;

public class ExpenseManagementFactoryImplTest {
	ExpenseManagementFactoryImpl expenseManagementFactoryImpl = new ExpenseManagementFactoryImpl();

    @Test
    public void testGetExecutorWithValidCommand() {
    	List<String> list = new ArrayList<>();
    	list.add("John");
    	list.add("Alice");
        CommandModel command = new CommandModel(OperationEnum.MOVE_IN, list);
        
        ExpenseManagementService executor = expenseManagementFactoryImpl.getExecutor(command);
        assertTrue(executor instanceof MoveInServiceImpl);
    }


    @Test
    public void testGetExecutorWithNullCommand() {
        CommandModel command = null;
        ExpenseManagementService executor = expenseManagementFactoryImpl.getExecutor(command);
        assertNull(executor);
    }
}
