package com.anshumanprajapati.geektrustExpenseManagement.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class OperationEnumTest {
	@Test
    public void testGetNumberOfArgumentsWithSingleArgument() {
        OperationEnum operation = OperationEnum.MOVE_IN;
        assertEquals(1, operation.getNumberOfArguments().get("minArgs"));
        assertEquals(1, operation.getNumberOfArguments().get("maxArgs"));
    }

    @Test
    public void testGetNumberOfArgumentsWithRangeOfArguments() {
        OperationEnum operation = OperationEnum.SPEND;
        assertEquals(3, operation.getNumberOfArguments().get("minArgs"));
        assertEquals(4, operation.getNumberOfArguments().get("maxArgs"));
    }
}
