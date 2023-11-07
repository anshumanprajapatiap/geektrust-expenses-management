package com.anshumanprajapati.geektrustExpenseManagement.service.impl.mapper;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.anshumanprajapati.geektrustExpenseManagement.model.MemberModel;

public class SpendServiceMapperTest {

    @Test
    public void testSplitEqualToOwedMapValid() {
        SpendServiceMapper spendServiceMapper = new SpendServiceMapper();
        Map<String, MemberModel> members = new HashMap<>();
        members.put("Alice", new MemberModel("Alice"));
        members.put("Bob", new MemberModel("Bob"));
        members.put("Charlie", new MemberModel("Charlie"));

        int amount = 60;
        String spentBy = "Alice";
        List<String> spentFor = Arrays.asList("Bob", "Charlie");

        Map<String, Integer> result = spendServiceMapper.splitEqualToOwedMap(members, amount, spentBy, spentFor);

        assertNotNull(result);
        assertNotEquals(30, members.get("Bob").getExpenses().get("Alice"));
        assertNotEquals(30, members.get("Charlie").getExpenses().get("Alice"));
    }

    @Test
    public void testSplitEqualToOwedMapInvalidMember() {
        SpendServiceMapper spendServiceMapper = new SpendServiceMapper();
        Map<String, MemberModel> members = new HashMap<>();
        members.put("Alice", new MemberModel("Alice"));

        int amount = 60;
        String spentBy = "Alice";
        List<String> spentFor = Arrays.asList("Bob", "Charlie");

        Map<String, Integer> result = spendServiceMapper.splitEqualToOwedMap(members, amount, spentBy, spentFor);

        assertNull(result);
    }

    @Test
    public void testSplitEqualToOwedMapInvalidMembers() {
        SpendServiceMapper spendServiceMapper = new SpendServiceMapper();
        Map<String, MemberModel> members = new HashMap<>();

        int amount = 60;
        String spentBy = "Alice";
        List<String> spentFor = Arrays.asList("Bob", "Charlie");

        Map<String, Integer> result = spendServiceMapper.splitEqualToOwedMap(members, amount, spentBy, spentFor);

        assertNull(result);
    }
}
