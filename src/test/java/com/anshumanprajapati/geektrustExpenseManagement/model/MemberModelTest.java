package com.anshumanprajapati.geektrustExpenseManagement.model;

import com.anshumanprajapati.geektrustExpenseManagement.constant.ExpenseManagementConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MemberModelTest {

    private MemberModel member;

    @BeforeEach
    public void setUp() {
        member = new MemberModel("Alice");
        member.setExpensesInit();
        member.addTotalSelfDueExpense(0);
    }

    @Test
    public void testInitialization() {
        assertEquals("Alice", member.getName());
        assertEquals(ExpenseManagementConstants.Integer_0, member.getTotalDues());
    }

    @Test
    public void testSetTotalDues() {
        member.setTotalDues(100);
        assertEquals(100, member.getTotalDues());
    }

    @Test
    public void testExpensesInitialization() {
        Map<String, Integer> expenses = member.getExpenses();
        assertEquals(0, expenses.size());
    }

    @Test
    public void testUpdateExpenses() {
        member.updateExpenses("Bob", 50);
        Map<String, Integer> expenses = member.getExpenses();
        assertEquals(1, expenses.size());
        assertEquals(50, expenses.get("Bob"));
    }

    @Test
    public void testClearDuesWithSufficientFunds() {
        member.updateExpenses("Bob", 100);
        String result = member.clearDues("Bob", 50, true);
        Map<String, Integer> expenses = member.getExpenses();
        assertEquals("SUCCESS", result);
        assertEquals(50, expenses.get("Bob"));
        assertEquals(50, member.getTotalDues());
    }

    @Test
    public void testClearDuesWithInsufficientFunds() {
        member.updateExpenses("Bob", 50);
        String result = member.clearDues("Bob", 100, true);
        Map<String, Integer> expenses = member.getExpenses();
        assertEquals("INCORRECT_PAYMENT", result);
        assertEquals(50, expenses.get("Bob"));
        assertEquals(0, member.getTotalDues());
    }

    @Test
    public void testClearDuesWithUnknownMember() {
        String result = member.clearDues("Charlie", 50, true);
        Map<String, Integer> expenses = member.getExpenses();
        assertEquals("MEMBER_NOT_FOUND", result);
        assertEquals(0, expenses.size());
        assertEquals(0, member.getTotalDues());
    }

    @Test
    public void testClearDuesWithNegativeAmount() {
        member.updateExpenses("Bob", -50);
        String result = member.clearDues("Bob", 50, true);
        Map<String, Integer> expenses = member.getExpenses();
        assertEquals("INCORRECT_PAYMENT", result);
        assertEquals(-50, expenses.get("Bob"));
        assertEquals(0, member.getTotalDues());
    }
    
    @Test
    public void testClearDuesWithNegativeFalseAmount() {
        member.updateExpenses("Bob", 50);
        String result = member.clearDues("Bob", -50, false);
        Map<String, Integer> expenses = member.getExpenses();
        assertEquals("SUCCESS", result);
        assertEquals(0, expenses.get("Bob"));
        assertEquals(50, member.getTotalDues());
    }

    @Test
    public void testGetDuesNotEqual() {
    	member.updateExpenses("David", 30);
    	member.updateExpenses("Charlie", -100);
        member.updateExpenses("Bob", 50);
        
        member.setExpensesInit();

        Map<String, Integer> sortedDues = new HashMap<>();
        sortedDues.put("Bob 50", 50);
        sortedDues.put("Charlie 0", -100);
        sortedDues.put("David 30", 30);

        assertNotEquals(sortedDues.keySet(), member.getDues());
    }
    
    
}
