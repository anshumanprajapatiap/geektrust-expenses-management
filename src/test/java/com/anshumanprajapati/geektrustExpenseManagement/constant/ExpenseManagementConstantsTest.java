package com.anshumanprajapati.geektrustExpenseManagement.constant;

import com.anshumanprajapati.geektrustExpenseManagement.constant.ExpenseManagementConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenseManagementConstantsTest {

    @Test
    public void testGetOperationValueWithInputDataError() {
        // Test the getOperationValue method with the "InputDataError" input
        String input = "INPUT_DATA_ERROR";
        String expectedValue = "INPUT_DATA_ERROR";
        String actualValue = ExpenseManagementConstants.getOperationValue(input);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testGetOperationValueWithDefaultInput() {
        // Test the getOperationValue method with an unknown input
        String input = "UnknownInput";
        String expectedValue = "UnknownInput";
        String actualValue = ExpenseManagementConstants.getOperationValue(input);
        assertEquals(expectedValue, actualValue);
    }
    
    @Test
    public void testGetOperationValue() {
        // Test the getOperationValue method with a valid input
        String validInput = "SUCCESS";
        String validOutput = ExpenseManagementConstants.getOperationValue(validInput);
        assertEquals(validInput, validOutput);

        // Test the getOperationValue method with an unknown input
        String unknownInput = "UNKNOWN_OPERATION";
        String unknownOutput = ExpenseManagementConstants.getOperationValue(unknownInput);
        assertEquals(unknownInput, unknownOutput);
    }
}
