package com.anshumanprajapati.geektrustExpenseManagement.converter;

import java.util.Arrays;
import java.util.List;

import com.anshumanprajapati.geektrustExpenseManagement.constant.ExpenseManagementConstants;


public class InputConverter {
	
	
	public List<String> postProcessString(String preProcessedText) throws Exception {
	    String[] processed = preProcessedText.split(ExpenseManagementConstants.space);
	    
	    if (processed.length == ExpenseManagementConstants.Integer_0) {
	        throw new Exception(ExpenseManagementConstants.INPUT_DATA_ERROR);
	    }

	    return Arrays.asList(processed);
	}

	
	
	

}
