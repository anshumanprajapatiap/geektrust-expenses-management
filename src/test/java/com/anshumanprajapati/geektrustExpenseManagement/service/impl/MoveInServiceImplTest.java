package com.anshumanprajapati.geektrustExpenseManagement.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.anshumanprajapati.geektrustExpenseManagement.constant.ExpenseManagementConstants;
import com.anshumanprajapati.geektrustExpenseManagement.enums.OperationEnum;
import com.anshumanprajapati.geektrustExpenseManagement.model.CommandModel;
import com.anshumanprajapati.geektrustExpenseManagement.model.MemberModel;

public class MoveInServiceImplTest {

    private MoveInServiceImpl moveInService;

    @BeforeEach
    public void setUp() {
        moveInService = new MoveInServiceImpl();
    }

    @Test
    public void testMoveInSuccess() {
        Map<String, MemberModel> members = new HashMap<>();
        CommandModel command = new CommandModel(null, Arrays.asList("Alice"));

        String result = moveInService.moveIn("Alice", members);

        assertEquals(ExpenseManagementConstants.SUCCESS, result);
    }

    @Test
    public void testMoveInHouseFull() {
        Map<String, MemberModel> members = new HashMap<>();
        members.put("Alice", new MemberModel("Alice"));
        members.put("Bob", new MemberModel("Bob"));
        members.put("Karma", new MemberModel("Karma"));

        CommandModel command = new CommandModel(OperationEnum.MOVE_IN, Arrays.asList("Charlie"));

        String result = moveInService.moveIn("Charlie", members);

        assertEquals(ExpenseManagementConstants.HOUSEFUL, result);
    }
}
