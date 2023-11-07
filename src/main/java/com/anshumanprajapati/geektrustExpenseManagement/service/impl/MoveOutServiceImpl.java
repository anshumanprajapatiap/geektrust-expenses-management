package com.anshumanprajapati.geektrustExpenseManagement.service.impl;

import java.util.Map;

import com.anshumanprajapati.geektrustExpenseManagement.constant.ExpenseManagementConstants;
import com.anshumanprajapati.geektrustExpenseManagement.exception.ExpenseExceptionHandler;
import com.anshumanprajapati.geektrustExpenseManagement.model.CommandModel;
import com.anshumanprajapati.geektrustExpenseManagement.model.MemberModel;
import com.anshumanprajapati.geektrustExpenseManagement.service.ExpenseManagementService;

public class MoveOutServiceImpl implements ExpenseManagementService{

	@Override
	public void expenseManagementServiceExecuter(Map<String, MemberModel> members, CommandModel command)
			throws Exception, ExpenseExceptionHandler {
		// TODO Auto-generated method stub
		String result = moveOut(members, command.getCommandParams().get(ExpenseManagementConstants.Integer_0));
		System.out.println(result);
		
	}
	
	public String moveOut(Map<String, MemberModel> members, String memberName) {
        if (members.containsKey(memberName)) {
        	return processMoveOut(members, memberName);
        } else {
            return ExpenseManagementConstants.MEMBER_NOT_FOUND;
        }
    }
	
	public String processMoveOut(Map<String, MemberModel> members, String memberName) {
		 if (members.get(memberName).getTotalDues() == ExpenseManagementConstants.Integer_0) {
             members.remove(memberName);
             return ExpenseManagementConstants.SUCCESS;
         } 
         return ExpenseManagementConstants.FAILURE;
         
	}

}
