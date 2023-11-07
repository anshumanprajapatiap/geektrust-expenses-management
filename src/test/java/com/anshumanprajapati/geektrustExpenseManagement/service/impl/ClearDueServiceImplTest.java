package com.anshumanprajapati.geektrustExpenseManagement.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.anshumanprajapati.geektrustExpenseManagement.constant.ExpenseManagementConstants;
import com.anshumanprajapati.geektrustExpenseManagement.enums.OperationEnum;
import com.anshumanprajapati.geektrustExpenseManagement.exception.ExpenseExceptionHandler;
import com.anshumanprajapati.geektrustExpenseManagement.model.CommandModel;
import com.anshumanprajapati.geektrustExpenseManagement.model.MemberModel;

public class ClearDueServiceImplTest {

    private ClearDueServiceImpl clearDueService;
    private Map<String, MemberModel> members;

    @BeforeEach
    public void setUp() {
        clearDueService = new ClearDueServiceImpl();
        members = new HashMap<>();
        members.put("Alice", new MemberModel("Alice"));
        members.put("Bob", new MemberModel("Bob"));
    }

    @Test
    public void testClearDueSuccess() throws Exception, ExpenseExceptionHandler {
        CommandModel command = new CommandModel(OperationEnum.CLEAR_DUE, 
        		Arrays.asList("Alice", "Bob", "50"));
        clearDueService.expenseManagementServiceExecuter(members, command);
        assertNotEquals(-50, members.get("Bob").getExpenses().get("Alice"));
    }

    @Test
    public void testClearDueMemberNotFound() throws Exception, ExpenseExceptionHandler {
        CommandModel command = new CommandModel(OperationEnum.CLEAR_DUE, 
        		Arrays.asList("Alice", "Charlie", "50"));
        clearDueService.expenseManagementServiceExecuter(members, command);
        assertEquals(0, members.get("Alice").getExpenses().size());
    }

    @Test
    public void testClearDueInvalidAmount() throws Exception, ExpenseExceptionHandler {
        CommandModel command = new CommandModel(OperationEnum.CLEAR_DUE, 
        		Arrays.asList("Alice", "Bob", "invalid"));
        clearDueService.expenseManagementServiceExecuter(members, command);
        assertEquals("INPUT_AMOUNT_ERROR", ExpenseManagementConstants.INPUT_AMOUNT_ERROR);
    }
}
