package com.anshumanprajapati.geektrustExpenseManagement.algorithm;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.anshumanprajapati.geektrustExpenseManagement.common.GlobalMethod;
import com.anshumanprajapati.geektrustExpenseManagement.constant.ExpenseManagementConstants;
import com.anshumanprajapati.geektrustExpenseManagement.model.MemberModel;

public class DebtSimplificationAlgorithm {
	
	Map<String, MemberModel> members;
	Map<MemberModel, Integer> netCashFlow = new HashMap<>();
	Map<MemberModel, Integer> givers = new HashMap<>();
	Map<MemberModel, Integer> receivers = new HashMap<>();
	boolean isSimplified = false;
	GlobalMethod globalMethod = new GlobalMethod();
	
	public DebtSimplificationAlgorithm(Map<String, MemberModel> membersMap){
		this.members = membersMap;
	}
	
	public void simplifyDebt() {
		calculateTotalDue();
		calculateNetCashFlow();
		calculateGiversAndReceivers();
		globalMethod.initializeMemberExpenses(members);
		simplifyDebtAlgo();
		

	}
	
	private void calculateTotalDue() {
	    Map<String, Integer> dueMap = new HashMap<>();

	    members.values().forEach(member -> {
	        dueMap.put(member.getName(), dueMap.getOrDefault(member.getName(), ExpenseManagementConstants.Integer_0));
	        member.getExpenses().forEach((name, amount) -> {
	            dueMap.put(name, dueMap.getOrDefault(name, ExpenseManagementConstants.Integer_0) + amount);
	        });
	    });

	    updateTotalDue(dueMap);
	}


	
	private void updateTotalDue(Map<String, Integer> dueMap) {
		for(String due: dueMap.keySet()) {
			members.get(due).setTotalDues(dueMap.get(due));
		}
	}

	private void calculateNetCashFlow(){
		for (MemberModel member : members.values()) {
			int netChangeInCash = member.getExpenses().values().stream()
			        .mapToInt(Integer::intValue)
			        .sum();
	        netCashFlow.put(member, netChangeInCash * -ExpenseManagementConstants.Integer_1);
	    }

	}
	
	private void calculateGiversAndReceivers() {
	    netCashFlow.forEach((member, netChangeInCash) -> {
	        if (netChangeInCash > ExpenseManagementConstants.Integer_0) {
	            givers.put(member, netChangeInCash);
	        } else if (netChangeInCash < ExpenseManagementConstants.Integer_0) {
	            receivers.put(member, -netChangeInCash);
	        }
	    });
	}

	
//	private void simplifyDebtAlgo() {
//	    List<MemberModel> giversSorted = sortMembersByAmount(givers);
//	    List<MemberModel> receiversSorted = sortMembersByAmount(receivers);
//
//	    for (MemberModel receiver : receiversSorted) {
//	        int remainingAmount = receivers.get(receiver);
//
//	        for (MemberModel giver : giversSorted) {
//	            int amountToGive = givers.get(giver);
//
//	            if (amountToGive >= remainingAmount) {
//	                settleDues(giver, receiver, remainingAmount);
//	                break;
//	            } else if (amountToGive > ExpenseManagementConstants.Integer_0) {
//	                settleDues(giver, receiver, amountToGive);
//	                remainingAmount -= amountToGive;
//	            }
//	        }
//	    }
//	}
	
	private void simplifyDebtAlgo() {
	    List<MemberModel> giversSorted = sortMembersByAmount(givers);
	    List<MemberModel> receiversSorted = sortMembersByAmount(receivers);

	    for (MemberModel receiver : receiversSorted) {
	        int remainingAmount = receivers.get(receiver);
	        settleDuesWithGivers(giversSorted, receiver, remainingAmount);
	    }
	}

	private void settleDuesWithGivers(List<MemberModel> giversSorted, MemberModel receiver, int remainingAmount) {
	    for (MemberModel giver : giversSorted) {
	        int amountToGive = givers.get(giver);

	        if (amountToGive >= remainingAmount) {
	            settleDues(giver, receiver, remainingAmount);
	            break;
	        } else if (amountToGive > ExpenseManagementConstants.Integer_0) {
	            settleDues(giver, receiver, amountToGive);
	            remainingAmount -= amountToGive;
	        }
	    }
	}

	
	private List<MemberModel> sortMembersByAmount(Map<MemberModel, Integer> amountMap) {
	    return amountMap.keySet()
	        .stream()
	        .sorted(Comparator.comparing(amountMap::get).reversed())
	        .collect(Collectors.toList());
	}

	private void settleDues(MemberModel giver, MemberModel receiver, int amount) {
	    performUpdation(giver, receiver, amount);
	}
	
	private void performUpdation(MemberModel giver, MemberModel receiver, int amount) {
	    
//	    System.out.println(giver.getName() + " will recive " + amount + " from " + receiver.getName());
	 
	    members.get(receiver.getName()).updateExpenses(giver.getName(),amount);
		members.get(giver.getName()).updateExpenses(receiver.getName(),-amount);
	}

}
