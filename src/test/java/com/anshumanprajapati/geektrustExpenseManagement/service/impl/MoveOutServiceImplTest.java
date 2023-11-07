package com.anshumanprajapati.geektrustExpenseManagement.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.anshumanprajapati.geektrustExpenseManagement.constant.ExpenseManagementConstants;
import com.anshumanprajapati.geektrustExpenseManagement.model.MemberModel;

public class MoveOutServiceImplTest {

    private MoveOutServiceImpl moveOutService;

    @BeforeEach
    public void setUp() {
        moveOutService = new MoveOutServiceImpl();
    }

    @Test
    public void testMoveOutSuccess() {
        Map<String, MemberModel> members = new HashMap<>();
        MemberModel member = new MemberModel("Alice");
        member.setTotalDues(0);
        members.put("Alice", member);

        String result = moveOutService.moveOut(members, "Alice");

        assertEquals(ExpenseManagementConstants.SUCCESS, result);
    }

    @Test
    public void testMoveOutFailureDueExists() {
        Map<String, MemberModel> members = new HashMap<>();
        MemberModel member = new MemberModel("Bob");
        member.setTotalDues(500);
        members.put("Bob", member);

        String result = moveOutService.moveOut(members, "Bob");

        assertEquals(ExpenseManagementConstants.FAILURE, result);
    }

    @Test
    public void testMoveOutMemberNotFound() {
        Map<String, MemberModel> members = new HashMap<>();

        String result = moveOutService.moveOut(members, "Charlie");

        assertEquals(ExpenseManagementConstants.MEMBER_NOT_FOUND, result);
    }
}
