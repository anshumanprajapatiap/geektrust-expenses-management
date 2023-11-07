package com.anshumanprajapati.geektrustExpenseManagement.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.anshumanprajapati.geektrustExpenseManagement.constant.ExpenseManagementConstants;
import com.anshumanprajapati.geektrustExpenseManagement.model.MemberModel;

public class SpendServiceImplTest {

    private SpendServiceImpl spendService;

    @BeforeEach
    public void setUp() {
        spendService = new SpendServiceImpl();
    }

    @Test
    public void testSpendSuccess() {
        Map<String, MemberModel> members = new HashMap<>();
        MemberModel spentBy = new MemberModel("Alice");
        spentBy.setTotalDues(0);

        MemberModel spentFor1 = new MemberModel("Bob");
        spentFor1.setTotalDues(0);
        MemberModel spentFor2 = new MemberModel("Charlie");
        spentFor2.setTotalDues(0);

        members.put("Alice", spentBy);
        members.put("Bob", spentFor1);
        members.put("Charlie", spentFor2);

        List<String> spentForList = Arrays.asList("Bob", "Charlie");

        String result = spendService.spend(members, 100, "Alice", spentForList);

        assertEquals(ExpenseManagementConstants.SUCCESS, result);
        assertEquals(-33, spentBy.getExpenses().get("Bob"));
        assertEquals(-33, spentBy.getExpenses().get("Charlie"));
        assertEquals(33, spentFor1.getExpenses().get("Alice"));
        assertEquals(33, spentFor2.getExpenses().get("Alice"));
    }

    @Test
    public void testSpendMemberNotFound() {
        Map<String, MemberModel> members = new HashMap<>();
        MemberModel spentBy = new MemberModel("Alice");
        spentBy.setTotalDues(0);

        members.put("Alice", spentBy);

        List<String> spentForList = Arrays.asList("Bob");

        String result = spendService.spend(members, 100, "Alice", spentForList);

        assertEquals(ExpenseManagementConstants.MEMBER_NOT_FOUND, result);
    }
}
