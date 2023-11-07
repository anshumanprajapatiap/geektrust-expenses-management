package com.anshumanprajapati.geektrustExpenseManagement;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.anshumanprajapati.geektrustExpenseManagement.constant.ExpenseManagementConstants;
import com.anshumanprajapati.geektrustExpenseManagement.controller.ExpenseManagementController;


public class ExpenseManagementApplication {
    public static void main(String[] args) throws Exception {
    	if (args.length != ExpenseManagementConstants.Integer_1) {
    		ExpenseManagementController controller = new ExpenseManagementController("sample_input/input1.txt");
	    	controller.execute();
            throw new FileNotFoundException(ExpenseManagementConstants.FileNotFound);
        }
    	
    	try {
	    	ExpenseManagementController controller = new ExpenseManagementController(args[ExpenseManagementConstants.Integer_0]);
	    	controller.execute();

        } catch (IOException ex) {
        	System.out.println(ex.getMessage());
        }
    }
}
