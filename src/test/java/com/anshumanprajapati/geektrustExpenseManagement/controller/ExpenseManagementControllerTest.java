package com.anshumanprajapati.geektrustExpenseManagement.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import com.anshumanprajapati.geektrustExpenseManagement.constant.ExpenseManagementConstants;

public class ExpenseManagementControllerTest {
    private static final String SAMPLE_INPUT_FILE = "sample_input/input1.txt";

    @Test
    public void testExecuteWithValidInput() throws Exception {
        // Redirect System.out to capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            ExpenseManagementController controller = new ExpenseManagementController(SAMPLE_INPUT_FILE);
            controller.execute();

            // Verify the output contains "SUCCESS" from your business logic
            assertTrue(outContent.toString().contains(ExpenseManagementConstants.SUCCESS));
        } catch (FileNotFoundException ex) {
            fail("File not found: " + ex.getMessage());
        } finally {
            // Restore the original System.out
            System.setOut(System.out);
        }
    }

//    @Test
    public void testExecuteWithInvalidInput() {
        // Redirect System.out to capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            ExpenseManagementController controller = new ExpenseManagementController("nonexistent_file.txt");
            controller.execute();

            // Verify the output contains "File Not Found"
            assertTrue(outContent.toString().contains(ExpenseManagementConstants.FileNotFound));
        } catch (Exception ex) {
            fail("Exception occurred: " + ex.getMessage());
        } finally {
            // Restore the original System.out
            System.setOut(System.out);
        }
    }
}
