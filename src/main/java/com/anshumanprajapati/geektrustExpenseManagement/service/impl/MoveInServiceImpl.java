package com.anshumanprajapati.geektrustExpenseManagement.service.impl;

import java.util.Map;

import com.anshumanprajapati.geektrustExpenseManagement.common.GlobalMethod;
import com.anshumanprajapati.geektrustExpenseManagement.constant.ExpenseManagementConstants;
import com.anshumanprajapati.geektrustExpenseManagement.exception.ExpenseExceptionHandler;
import com.anshumanprajapati.geektrustExpenseManagement.model.CommandModel;
import com.anshumanprajapati.geektrustExpenseManagement.model.MemberModel;
import com.anshumanprajapati.geektrustExpenseManagement.service.ExpenseManagementService;

public class MoveInServiceImpl implements ExpenseManagementService{
	
	GlobalMethod globalMethod = new GlobalMethod();

	@Override
	public void expenseManagementServiceExecuter(Map<String, MemberModel> members, CommandModel command)
			throws Exception, ExpenseExceptionHandler {
		// TODO Auto-generated method stub
		String MemberName = command.getCommandParams().get(ExpenseManagementConstants.Integer_0);
		String result = moveIn(MemberName, members);
		System.out.println(result);
		
	}
	
	
	public String moveIn(String memberName, Map<String, MemberModel> members) {
        if (members.size() < ExpenseManagementConstants.MaxNumberOFMember) {
            members.put(memberName, new MemberModel(memberName));
            globalMethod.initializeMemberExpenses(members);
            return ExpenseManagementConstants.SUCCESS;
        } else {
            return ExpenseManagementConstants.HOUSEFUL;
        }
    }
	

}
