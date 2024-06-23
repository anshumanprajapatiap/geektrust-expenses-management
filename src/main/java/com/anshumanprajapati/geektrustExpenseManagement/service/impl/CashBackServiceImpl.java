package com.anshumanprajapati.geektrustExpenseManagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.anshumanprajapati.geektrustExpenseManagement.enums.OperationEnum;
import com.anshumanprajapati.geektrustExpenseManagement.exception.ExpenseExceptionHandler;
import com.anshumanprajapati.geektrustExpenseManagement.model.CommandModel;
import com.anshumanprajapati.geektrustExpenseManagement.model.MemberModel;
import com.anshumanprajapati.geektrustExpenseManagement.service.ExpenseManagementService;

public class CashBackServiceImpl implements ExpenseManagementService{
	
	ClearDueServiceImpl clearDueServiceImpl;

	@Override
	public void expenseManagementServiceExecuter(Map<String, MemberModel> members, CommandModel command)
			throws Exception, ExpenseExceptionHandler {
		
		// CASHBACK 10 A B C
		clearDueServiceImpl = new ClearDueServiceImpl();

		String amount = command.getCommandParams().get(0);
		String paidBy =  command.getCommandParams().get(1);
		List<String> paidFor =  command.getCommandParams().subList(2, command.getCommandParams().size());
		
	}
	
	
	void cashBack(Map<String, MemberModel> members, String amount, String paidBy, List<String> paidFor){
		
		Integer amountInt = Integer.parseInt(amount);
		
		
		int n = paidFor.size();
		amountInt = amountInt/(n+1);
		
		for(int i=0; i<n; i++) {
			
			List<String> parm = new ArrayList<>();
			parm.add(paidFor.get(i));
			parm.add(paidBy);
			parm.add(amountInt.toString());
			
			CommandModel clearOutCommand = new CommandModel(OperationEnum.CLEAR_DUE, parm);
			
			try {
				clearDueServiceImpl.expenseManagementServiceExecuter(members, clearOutCommand);
			} catch (ExpenseExceptionHandler e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	
	
	
	

}
