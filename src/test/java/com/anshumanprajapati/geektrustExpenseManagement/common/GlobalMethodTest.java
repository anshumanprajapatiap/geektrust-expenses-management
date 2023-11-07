package com.anshumanprajapati.geektrustExpenseManagement.common;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.anshumanprajapati.geektrustExpenseManagement.model.MemberModel;

public class GlobalMethodTest {
    private Map<String, MemberModel> members;
    GlobalMethod globalMethod = new GlobalMethod();

    @BeforeEach
    public void setUp() {
        members = new HashMap<>();
    }

    @Test
    public void testInitializeMemberExpenses() {
        MemberModel member1 = new MemberModel("Alice");
        MemberModel member2 = new MemberModel("Bob");
        MemberModel member3 = new MemberModel("Charlie");

        members.put("Alice", member1);
        members.put("Bob", member2);
        members.put("Charlie", member3);

        globalMethod.initializeMemberExpenses(members);

        // Check that each member's expenses are initialized
        for (MemberModel member : members.values()) {
            assertNotNull(member.getExpenses());
        }

        // Check that expenses for each member are set to 0 for others
        for (MemberModel member : members.values()) {
            for (MemberModel otherMember : members.values()) {
                if (!member.equals(otherMember)) {
                    assertEquals(0, (int) member.getExpenses().get(otherMember.getName()));
                }
            }
        }
    }

    @Test
    public void testInitializeMemberExpensesWithSingleMember() {
        MemberModel member1 = new MemberModel("Alice");
        members.put("Alice", member1);

        globalMethod.initializeMemberExpenses(members);

        // Check that the member's expenses are initialized
        assertNotNull(member1.getExpenses());

        // Check that expenses for the single member are set to 0 (there are no other members)
//        assertNotEquals(0, (int) member1.getExpenses().get("Alice"));
//        assertThrows(NullPointerException.class, () -> {
//        	member1.getExpenses().get("Alice");
//        });
    }

    @Test
    public void testInitializeMemberExpensesWithNoMembers() {
    	globalMethod.initializeMemberExpenses(members);

        // Ensure there are no exceptions or errors
    }
}

