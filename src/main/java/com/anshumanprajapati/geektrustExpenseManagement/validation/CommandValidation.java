package com.anshumanprajapati.geektrustExpenseManagement.validation;

import java.util.EnumSet;

import com.anshumanprajapati.geektrustExpenseManagement.constant.ExpenseManagementConstants;
import com.anshumanprajapati.geektrustExpenseManagement.enums.OperationEnum;
import com.anshumanprajapati.geektrustExpenseManagement.model.CommandModel;

public class CommandValidation {
	
	
	public boolean validateCommand(CommandModel command) {
		
		
		if(!validateOperation(command.getInputCommand())) {
			return false;
		}
		if(!validateInputCommand(command.getInputCommand(), command)) {
			return false;
		}
		
		
		
		return true;
		
	}
	
	private boolean validateOperation(OperationEnum operation) {
		
		return EnumSet.allOf(OperationEnum.class).contains(operation);
	}
	
	
	
	private boolean validateInputCommand(OperationEnum inputCommand , CommandModel command){
        int paramSize = command.getCommandParams().size();
        int minParamAccepted = inputCommand.getNumberOfArguments().get(ExpenseManagementConstants.minArgs);
        int maxParamAccepted = inputCommand.getNumberOfArguments().get(ExpenseManagementConstants.maxArgs);
		if(paramSize < minParamAccepted || paramSize > maxParamAccepted){
//			System.out.print(paramSize + " Min -> " + minParamAccepted + " Max -> " + maxParamAccepted + " ERRORAYEGA ");
            return false;
        }
		return true;
    }

}
