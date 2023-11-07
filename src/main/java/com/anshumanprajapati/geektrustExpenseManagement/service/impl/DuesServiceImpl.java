package com.anshumanprajapati.geektrustExpenseManagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.anshumanprajapati.geektrustExpenseManagement.constant.ExpenseManagementConstants;
import com.anshumanprajapati.geektrustExpenseManagement.exception.ExpenseExceptionHandler;
import com.anshumanprajapati.geektrustExpenseManagement.model.CommandModel;
import com.anshumanprajapati.geektrustExpenseManagement.model.MemberModel;
import com.anshumanprajapati.geektrustExpenseManagement.service.ExpenseManagementService;

public class DuesServiceImpl implements ExpenseManagementService{
	
	

	@Override
	public void expenseManagementServiceExecuter(Map<String, MemberModel> members, CommandModel command)
			throws Exception, ExpenseExceptionHandler {
		// TODO Auto-generated method stub
		
		String name = command.getCommandParams().get(ExpenseManagementConstants.Integer_0);
		List<String> result = dues(members, name);
		printList(result);
		
	}
	
	
	public List<String> dues(Map<String, MemberModel> members, String memberName) {
        if (members.containsKey(memberName)) {
//        	System.out.print( members.get(memberName).getTotalDues() + " -> ");
            return members.get(memberName).getDues();
        } else {
            List<String> errorMessage = new ArrayList<>();
            errorMessage.add(ExpenseManagementConstants.MEMBER_NOT_FOUND);
            return errorMessage;
        }
    }
	
	public void printList(List<String> list){
		for(String value: list) {
			System.out.println(value);
		}
//		System.out.println(list);
	}



}
