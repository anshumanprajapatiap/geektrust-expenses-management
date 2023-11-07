package com.anshumanprajapati.geektrustExpenseManagement.service.impl;

import java.util.Map;

import com.anshumanprajapati.geektrustExpenseManagement.algorithm.DebtSimplificationAlgorithm;
import com.anshumanprajapati.geektrustExpenseManagement.constant.ExpenseManagementConstants;
import com.anshumanprajapati.geektrustExpenseManagement.exception.ExpenseExceptionHandler;
import com.anshumanprajapati.geektrustExpenseManagement.model.CommandModel;
import com.anshumanprajapati.geektrustExpenseManagement.model.MemberModel;
import com.anshumanprajapati.geektrustExpenseManagement.service.ExpenseManagementService;

public class ClearDueServiceImpl implements ExpenseManagementService {
	
	DebtSimplificationAlgorithm simplificationAlgo;

	@Override
	public void expenseManagementServiceExecuter(Map<String, MemberModel> members, CommandModel command)
			throws Exception, ExpenseExceptionHandler {
		// TODO Auto-generated method stub
		simplificationAlgo = new DebtSimplificationAlgorithm(members);
		String result = clearDueMainMethod(members, command);
		System.out.println(result);
		
	}
	
	private String clearDueMainMethod(Map<String, MemberModel> members, CommandModel command) {
		String sender = command.getCommandParams().get(ExpenseManagementConstants.Integer_0);
		String reciver = command.getCommandParams().get(ExpenseManagementConstants.Integer_1);
		String amountInString = command.getCommandParams().get(ExpenseManagementConstants.Integer_2);
		String result = null;
		try {
			
			int amountIntValue = Integer.parseInt(amountInString);
			result = clearDue(members, sender, reciver, amountIntValue);
			 
		} catch (NumberFormatException e) {
		    result = ExpenseManagementConstants.INPUT_AMOUNT_ERROR;
		} 
		return result;
		
	}
	
	
	
	private String clearDue(Map<String, MemberModel> members, String sender, String reciver, int amount) {
        if (members.containsKey(sender) && members.containsKey(reciver)) {
             return clearForSenderNReciver(members, sender, reciver, amount); 
            } else {
            return ExpenseManagementConstants.MEMBER_NOT_FOUND;
        }
	}
	
	private String clearForSenderNReciver(Map<String, MemberModel> members, String sender, String reciver, int amount) {
		members.get(sender).clearDues(reciver, amount, ExpenseManagementConstants.TRUE);
		String result = members.get(reciver).clearDues(sender, amount, ExpenseManagementConstants.FALSE);
		if(result==ExpenseManagementConstants.SUCCESS) {
			result = String.valueOf(members.get(sender).getExpenses().get(reciver));
		}
		return result;
	}
	

}
