package com.anshumanprajapati.geektrustExpenseManagement.enums;

import java.util.HashMap;
import java.util.Map;

import com.anshumanprajapati.geektrustExpenseManagement.constant.ExpenseManagementConstants;

public enum OperationEnum {
	
	MOVE_IN(ExpenseManagementConstants.Integer_1),
	SPEND(ExpenseManagementConstants.Integer_3, ExpenseManagementConstants.Integer_4),
	DUES(ExpenseManagementConstants.Integer_1),
	CLEAR_DUE(ExpenseManagementConstants.Integer_3),
	MOVE_OUT(ExpenseManagementConstants.Integer_1);

	OperationEnum(Integer numArgs){
		this.minArgument = numArgs;
		this.maxArgument = numArgs;
    
    }
	OperationEnum(Integer minArgs, Integer maxArgs){
		this.minArgument = minArgs;
		this.maxArgument = maxArgs;
    }
    
    private final Integer minArgument;
    private final Integer maxArgument;

    public Map<String, Integer> getNumberOfArguments() {
    	Map<String, Integer> minMaxArgs = new HashMap<>();
    	minMaxArgs.put(ExpenseManagementConstants.minArgs, minArgument);
    	minMaxArgs.put(ExpenseManagementConstants.maxArgs, maxArgument);
        return minMaxArgs;
    }

}
