package com.anshumanprajapati.geektrustExpenseManagement.algorithm;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.anshumanprajapati.geektrustExpenseManagement.algorithm.DebtSimplificationAlgorithm;
import com.anshumanprajapati.geektrustExpenseManagement.model.MemberModel;

public class DebtSimplificationAlgorithmTest {
    private DebtSimplificationAlgorithm algorithm;
    private Map<String, MemberModel> members;

    @BeforeEach
    public void setUp() {
        members = new HashMap<>();

        MemberModel member1 = new MemberModel("Alice");
        MemberModel member2 = new MemberModel("Bob");
        MemberModel member3 = new MemberModel("Charlie");
        MemberModel member4 = new MemberModel("Don");

        members.put("Alice", member1);
        members.put("Bob", member2);
        members.put("Charlie", member3);
        members.put("Don", member4);

        algorithm = new DebtSimplificationAlgorithm(members);
    }

    @Test
    public void testSimplifyDebt() {
        // Simulate some expenses and dues (adjust this as needed)
        MemberModel alice = members.get("Alice");
        MemberModel bob = members.get("Bob");
        MemberModel charlie = members.get("Charlie");

        alice.updateExpenses("Bob", 20);
        bob.updateExpenses("Alice", 10);
        charlie.updateExpenses("Bob", 30);
        charlie.updateExpenses("Alice", 40);

        algorithm.simplifyDebt();

        // Validate that the debts are simplified as expected
        assertEquals(50, alice.getTotalDues());
        assertEquals(50, bob.getTotalDues());
        assertEquals(0, charlie.getTotalDues());
    }
    
    
    
    @Test
    public void testSimplifyDebt2() {
        // Simulate some expenses and dues (adjust this as needed)
        MemberModel alice = members.get("Alice");
        MemberModel bob = members.get("Bob");
        MemberModel charlie = members.get("Charlie");
        MemberModel don = members.get("Don");

        alice.updateExpenses("Bob", 30); // Increase Alice's expenses
        bob.updateExpenses("Charlie", -30);
        charlie.updateExpenses("Don", 40);
        don.updateExpenses("Alice", -10);

        algorithm.simplifyDebt();

        // Validate that the debts are simplified as expected
        assertEquals(-10, alice.getTotalDues()); // Alice owes nothing
        assertEquals(30, bob.getTotalDues()); // Bob owes Charlie 20
        assertEquals(-30, charlie.getTotalDues()); // Charlie owes Don 10
        assertEquals(40, don.getTotalDues()); // Don owes Alice 10
    }

    @Test
    public void testSimplifyDebtWithEmptyMembers() {
        // Test when there are no members
        algorithm = new DebtSimplificationAlgorithm(new HashMap<>());
        algorithm.simplifyDebt();

        // No exceptions or errors expected
    }

    @Test
    public void testSimplifyDebtWithSingleMember() {
        // Test when there is only one member
        members = new HashMap<>();
        MemberModel member1 = new MemberModel("Alice");
        members.put("Alice", member1);
        algorithm = new DebtSimplificationAlgorithm(members);

        algorithm.simplifyDebt();

        // No exceptions or errors expected
    }
}
