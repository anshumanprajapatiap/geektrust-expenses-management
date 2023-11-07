package com.anshumanprajapati.geektrustExpenseManagement.service.impl.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.anshumanprajapati.geektrustExpenseManagement.constant.ExpenseManagementConstants;
import com.anshumanprajapati.geektrustExpenseManagement.model.MemberModel;

public class SpendServiceMapper {

	public Map<String, Integer> splitEqualToOwedMap(Map<String, MemberModel> members, int amount, String spentBy, List<String> spentFor) {
	
		if (members.containsKey(spentBy) && 
        		spentFor.stream().allMatch(members::containsKey)) {
			int sharedAmount = amount / (spentFor.size() + ExpenseManagementConstants.Integer_1);

            return getOwedAmountMap(members, spentFor, sharedAmount);
        } else {
            return null;
        }
	}
	
	private Map<String, Integer> getOwedAmountMap(Map<String, MemberModel> members, List<String> spentFor, int sharedAmount){
		Map<String, Integer> owedAmountsKVM = new HashMap<>();

        for (String memberName : spentFor) {
        	owedAmountsKVM.put(memberName, sharedAmount);
        }
		return owedAmountsKVM;
		
	}
	

}
