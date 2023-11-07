package com.anshumanprajapati.geektrustExpenseManagement.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.anshumanprajapati.geektrustExpenseManagement.constant.ExpenseManagementConstants;
import com.anshumanprajapati.geektrustExpenseManagement.converter.CommandConverter;
import com.anshumanprajapati.geektrustExpenseManagement.exception.ExpenseExceptionHandler;
import com.anshumanprajapati.geektrustExpenseManagement.factory.impl.ExpenseManagementFactoryImpl;
import com.anshumanprajapati.geektrustExpenseManagement.model.CommandModel;
import com.anshumanprajapati.geektrustExpenseManagement.model.MemberModel;
import com.anshumanprajapati.geektrustExpenseManagement.service.ExpenseManagementService;
import com.anshumanprajapati.geektrustExpenseManagement.service.InputService;
import com.anshumanprajapati.geektrustExpenseManagement.service.impl.FileInputServiceImpl;
import com.anshumanprajapati.geektrustExpenseManagement.validation.CommandValidation;

public class ExpenseManagementController {
	
	private Map<String, MemberModel> members;
	private final InputService fileService;
	private final ExpenseManagementFactoryImpl expenseManagementFactoryImpl = new ExpenseManagementFactoryImpl();
	
	CommandConverter commandConverted = new CommandConverter();
	CommandValidation commandValidation = new CommandValidation();
	
	
	public ExpenseManagementController(String filePath) throws FileNotFoundException {
        fileService = new FileInputServiceImpl(filePath);
        members = new HashMap<>();
    }
	
	
	public void execute() throws Exception {
	    List<String> commands = getCommandList();
	    for (String command : commands) {
	        executeCommand(command);
	    }
	}

	private void executeCommand(String command) throws Exception {
	    CommandModel commandModel = commandConverted.getCommandFromString(command);

	    if (commandValidation.validateCommand(commandModel)) {
	        try {
	            executeCommandService(commandModel);
	        } catch (ExpenseExceptionHandler e) {
	            handleExpenseExceptionHandler(e);
	        }
	    } else {
	        // System.out.print(command + " -> ");
	        System.out.println(ExpenseManagementConstants.INPUT_DATA_ERROR);
	    }
	}

	private void executeCommandService(CommandModel commandModel) throws Exception, ExpenseExceptionHandler {
	    ExpenseManagementService executorConcreteObj = expenseManagementFactoryImpl.getExecutor(commandModel);
	    executorConcreteObj.expenseManagementServiceExecuter(members, commandModel);
	}

	private void handleExpenseExceptionHandler(ExpenseExceptionHandler e) {
	    System.out.println(e.getMessage());
	}


	public List<String> getCommandList() throws IOException{
		return fileService.getCommandList();
		
	}

}
