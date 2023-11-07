package com.anshumanprajapati.geektrustExpenseManagement.common;

import java.util.Collection;
import java.util.Map;

import com.anshumanprajapati.geektrustExpenseManagement.constant.ExpenseManagementConstants;
import com.anshumanprajapati.geektrustExpenseManagement.model.MemberModel;

public class GlobalMethod {
	
	
	public void initializeMemberExpenses(Map<String, MemberModel> members) {
	    if (members.size() == ExpenseManagementConstants.Integer_1) {
	        return;
	    }

	    members.values().forEach(member -> initializeMemberExpensesIfNeeded(member, members.values()));
	}
	
	private void initializeMemberExpensesIfNeeded(MemberModel member, Collection<MemberModel> otherMembers) {
	    if (member.getExpenses() == null) {
	        member.setExpensesInit();
	    }

	    initializeExpensesToZero(member, otherMembers);
	}
	
	private void initializeExpensesToZero(MemberModel member, Collection<MemberModel> otherMembers) {
	    otherMembers.stream()
	        .filter(otherMember -> !member.equals(otherMember))
	        .forEach(otherMember -> member.getExpenses().put(otherMember.getName(), ExpenseManagementConstants.Integer_0));
	}

	
	
}
