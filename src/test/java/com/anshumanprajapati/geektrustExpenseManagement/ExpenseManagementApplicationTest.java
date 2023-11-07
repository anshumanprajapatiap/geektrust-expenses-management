package com.anshumanprajapati.geektrustExpenseManagement;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class ExpenseManagementApplicationTest {
	
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testMainWithValidArgs() throws Exception {
        String[] args = {"sample_input/input1.txt"};
        ExpenseManagementApplication.main(args);

        String expectedOutput = "SUCCESS\n"
        		+ "SUCCESS\n"
        		+ "SUCCESS\n"
        		+ "HOUSEFUL\n"
        		+ "SUCCESS\n"
        		+ "SUCCESS\n"
        		+ "MEMBER_NOT_FOUND\n"
        		+ "ANDY 1150\n"
        		+ "WOODY 0\n"
        		+ "ANDY 850\n"
        		+ "BO 0\n"
        		+ "650\n"
        		+ "INCORRECT_PAYMENT\n"
        		+ "FAILURE\n"
        		+ "FAILURE\n"
        		+ "FAILURE\n"
        		+ "0\n"
        		+ "SUCCESS"; // Replace with the expected output
        String actualOutput = outContent.toString().trim();
        
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testMainWithInvalidArgs() throws Exception {
    	 String[] args = {};

    	    try {
    	        ExpenseManagementApplication.main(args);
    	    } catch (Exception e) {
    	        // Handle the exception and print an error message
    	        System.err.println(e.getMessage());
    	    }
  
    }
}
