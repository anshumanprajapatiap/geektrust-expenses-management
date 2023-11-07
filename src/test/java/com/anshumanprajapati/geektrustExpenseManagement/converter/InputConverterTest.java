package com.anshumanprajapati.geektrustExpenseManagement.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.anshumanprajapati.geektrustExpenseManagement.constant.ExpenseManagementConstants;

public class InputConverterTest {
	
	InputConverter inputConverter = new InputConverter();
    @Test
    public void testPostProccesStringWithValidData() {
        String validInput = "SPEND 100 Alice Bob";
        List<?> processedList;

        try {
            processedList = inputConverter.postProcessString(validInput);

            assertNotNull(processedList);
            assertEquals(4, processedList.size());
            assertEquals("SPEND", processedList.get(0));
            assertEquals("100", processedList.get(1));
            assertEquals("Alice", processedList.get(2));
            assertEquals("Bob", processedList.get(3));
        } catch (Exception e) {
            fail("Exception should not be thrown for valid data: " + e.getMessage());
        }
    }
    


}
