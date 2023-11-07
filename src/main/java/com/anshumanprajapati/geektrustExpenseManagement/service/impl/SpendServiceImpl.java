package com.anshumanprajapati.geektrustExpenseManagement.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.anshumanprajapati.geektrustExpenseManagement.algorithm.DebtSimplificationAlgorithm;
import com.anshumanprajapati.geektrustExpenseManagement.constant.ExpenseManagementConstants;
import com.anshumanprajapati.geektrustExpenseManagement.exception.ExpenseExceptionHandler;
import com.anshumanprajapati.geektrustExpenseManagement.model.CommandModel;
import com.anshumanprajapati.geektrustExpenseManagement.model.MemberModel;
import com.anshumanprajapati.geektrustExpenseManagement.service.ExpenseManagementService;
import com.anshumanprajapati.geektrustExpenseManagement.service.impl.mapper.SpendServiceMapper;

public class SpendServiceImpl implements ExpenseManagementService{
	
	
	SpendServiceMapper spendServiceMapper = new SpendServiceMapper();
	DebtSimplificationAlgorithm simplificationAlgo;

	@Override
	public void expenseManagementServiceExecuter(Map<String, MemberModel> members, CommandModel command)
			throws Exception, ExpenseExceptionHandler {
		// TODO Auto-generated method stub
		
		simplificationAlgo = new DebtSimplificationAlgorithm(members);
		processAddingSpend(members, command);
	}
	
	private void processAddingSpend(Map<String, MemberModel> members, CommandModel command) {
		try {
			String amountInString = command.getCommandParams().get(ExpenseManagementConstants.Integer_0);
			int amountIntValue = Integer.parseInt(amountInString);
			String result = spend(members, amountIntValue, command.getCommandParams().get(ExpenseManagementConstants.Integer_1), command.getCommandParams().subList(ExpenseManagementConstants.Integer_2, command.getCommandParams().size()));
			callSimplification(result);
			System.out.println(result);
			 
		} catch (NumberFormatException e) {
		    System.out.println(ExpenseManagementConstants.INPUT_AMOUNT_ERROR);
		}
	}
	
	private void callSimplification(String result) {
		if(!StringUtils.equals(ExpenseManagementConstants.MEMBER_NOT_FOUND, result)) {
			simplificationAlgo.simplifyDebt();
		}
	}
	
	
	public String spend(Map<String, MemberModel> members, int amount, String spentBy, List<String> spentFor) {
//		System.out.print(spentFor);
//		System.out.print(" ");
		
		Map<String, Integer> owedAmounts = spendServiceMapper.splitEqualToOwedMap(members, amount, spentBy, spentFor);
		if(owedAmounts!=null) {
			return spendWithSplit(members, amount, spentBy, owedAmounts);
		}else {
			return ExpenseManagementConstants.MEMBER_NOT_FOUND;
		}
    }
	
	
	
	private String spendWithSplit(Map<String, MemberModel> members, int totalAmount, String spentBy, Map<String, Integer> owedUsers) {
	    if (members.containsKey(spentBy)) {
	    	
	    	for (String ownedUser : owedUsers.keySet()) {

	    		members.get(ownedUser).updateExpenses(spentBy, owedUsers.get(ownedUser));
	    		members.get(spentBy).updateExpenses(ownedUser, -owedUsers.get(ownedUser));

	        }

	        return ExpenseManagementConstants.SUCCESS;
	    } else {
	        return ExpenseManagementConstants.MEMBER_NOT_FOUND;
	    }
	}
	

}
