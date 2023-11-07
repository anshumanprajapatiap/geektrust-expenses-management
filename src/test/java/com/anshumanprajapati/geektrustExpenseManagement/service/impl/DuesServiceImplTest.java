package com.anshumanprajapati.geektrustExpenseManagement.service.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.anshumanprajapati.geektrustExpenseManagement.constant.ExpenseManagementConstants;
import com.anshumanprajapati.geektrustExpenseManagement.model.MemberModel;

public class DuesServiceImplTest {
    
    private DuesServiceImpl duesService;
    private Map<String, MemberModel> members;

    @BeforeEach
    public void setUp() {
        duesService = new DuesServiceImpl();
        members = new HashMap<>();

        // Create some member instances and add them to the members map
        MemberModel alice = new MemberModel("Alice");
        alice.setTotalDues(100);
        members.put("Alice", alice);

        MemberModel bob = new MemberModel("Bob");
        bob.setTotalDues(50);
        members.put("Bob", bob);
    }

    @Test
    public void testDuesForExistingMember() {
        String memberName = "Alice";
        List<String> duesList = duesService.dues(members, memberName);

        // Assert that the duesList is not null and contains expected dues information
        assertNotNull(duesList);
        assertFalse(duesList.contains("Bob 0"));
    }

    @Test
    public void testDuesForNonExistingMember() {
        String memberName = "Charlie";
        List<String> duesList = duesService.dues(members, memberName);

        // Assert that the duesList contains an error message
        assertNotNull(duesList);
        assertTrue(duesList.contains(ExpenseManagementConstants.MEMBER_NOT_FOUND));
    }
}

