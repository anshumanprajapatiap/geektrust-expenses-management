package com.anshumanprajapati.geektrustExpenseManagement.converter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.anshumanprajapati.geektrustExpenseManagement.constant.ExpenseManagementConstants;
import com.anshumanprajapati.geektrustExpenseManagement.enums.OperationEnum;
import com.anshumanprajapati.geektrustExpenseManagement.model.CommandModel;


public class CommandConverter {
	
	
	public CommandModel getCommandFromList(List<String> commandList) throws Exception {
	    if (commandList.isEmpty()) {
	        throw new Exception(ExpenseManagementConstants.INPUT_DATA_ERROR);
	    }

	    String operationString = commandList.get(ExpenseManagementConstants.Integer_0);
	    OperationEnum operator = parseOperationFromString(operationString);
	    List<String> commandParams = extractCommandParams(commandList);

	    return new CommandModel(operator, commandParams);
	}

	public CommandModel getCommandFromString(String command) throws Exception {
	    String[] commandOperationAndParams = command.split(ExpenseManagementConstants.space);

	    if (commandOperationAndParams.length < ExpenseManagementConstants.Integer_1) {
	        throw new Exception(ExpenseManagementConstants.INPUT_DATA_ERROR);
	    }

	    List<String> commandList = Arrays.asList(commandOperationAndParams);
	    return getCommandFromList(commandList);
	}

	private OperationEnum parseOperationFromString(String operationString) throws Exception {
	    String operationValue = ExpenseManagementConstants.getOperationValue(operationString);
	    return OperationEnum.valueOf(operationValue);
	}

	private List<String> extractCommandParams(List<String> commandList) {
	    return commandList.subList(1, commandList.size());
	}


}
