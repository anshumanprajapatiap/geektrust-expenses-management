package com.anshumanprajapati.geektrustExpenseManagement.service;

import java.util.Map;

import com.anshumanprajapati.geektrustExpenseManagement.exception.ExpenseExceptionHandler;
import com.anshumanprajapati.geektrustExpenseManagement.model.CommandModel;
import com.anshumanprajapati.geektrustExpenseManagement.model.MemberModel;


public interface ExpenseManagementService {
	
	void expenseManagementServiceExecuter(Map<String, MemberModel> members, CommandModel command) throws Exception, ExpenseExceptionHandler;

}
