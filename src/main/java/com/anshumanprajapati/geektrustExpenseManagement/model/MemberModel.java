	package com.anshumanprajapati.geektrustExpenseManagement.model;
	
	import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.anshumanprajapati.geektrustExpenseManagement.constant.ExpenseManagementConstants;
	
	public class MemberModel {
		
		
		private String name;
	    private int totalDues;
	    private Map<String, Integer> expenses;
	    
	
	    public MemberModel(String name) {
	        this.name = name;
	        this.totalDues = ExpenseManagementConstants.Integer_0;
	        this.expenses = new HashMap<>();
	    }
	
	    public String getName() {
	        return name;
	    }
	
	    
	    public int getTotalDues() {
	        return totalDues;
	    }
	    
	    public void setTotalDues(int totalDues) {
	        this.totalDues = totalDues;
	    }

	    
	    public Map<String, Integer> getExpenses(){
	    	return expenses;
	    }
	    
	    
	    public Map<String, Integer> setExpensesInit(){
	    	return this.expenses = new HashMap<>();
	    }
	    
	    
	    public void updateExpenses(String user, int amount) {
	        expenses.put(user, expenses.getOrDefault(user, 0) + amount);
	    }
	
	    public void addTotalSelfDueExpense(int due) {
	        totalDues += due;
	    }
	   
	    
	    
	    public String clearDues(String member, int amount, boolean isReceiver) {
	        if (!expenses.containsKey(member)) {
	            return "MEMBER_NOT_FOUND";
	        }

	        int currentExpenses = expenses.get(member);

	        if (isReceiver) {
	            return clearDuesForReceiver(member, amount, currentExpenses);
	        } else {
	            return clearDuesForPayer(member, amount, currentExpenses);
	        }
	    }

	    private String clearDuesForReceiver(String member, int amount, int currentExpenses) {
	        if (amount <= currentExpenses) {
	            expenses.put(member, currentExpenses - amount);
	            totalDues += amount;
	            return ExpenseManagementConstants.SUCCESS;
	        } else {
	            return ExpenseManagementConstants.INCORRECT_PAYMENT;
	        }
	    }

	    private String clearDuesForPayer(String member, int amount, int currentExpenses) {
	        if (currentExpenses + amount <= 0) {
	            expenses.put(member, currentExpenses + amount);
	            totalDues -= amount;
	            return ExpenseManagementConstants.SUCCESS;
	        } else {
	            return ExpenseManagementConstants.INCORRECT_PAYMENT;
	        }
	    }

	    
	
	    public List<String> getDues() {
//	    	Sorting Map
	        Map<String, Integer> sortedDues = new TreeMap<>(valueComparator);
	        sortedDues.putAll(expenses);

	        List<String> duesList = new ArrayList<>();
	        for (Map.Entry<String, Integer> entry : sortedDues.entrySet()) {
	        	Integer value = entry.getValue()< ExpenseManagementConstants.Integer_0 ? ExpenseManagementConstants.Integer_0 : entry.getValue();
	        	duesList.add(entry.getKey() + ExpenseManagementConstants.space + value);
	        }

	        return duesList;
	    }
	    
	    
	    Comparator<String> valueComparator = new Comparator<String>() {
	        @Override
	        public int compare(String s1, String s2) {
	            int value1 = expenses.get(s1);
	            int value2 = expenses.get(s2);
	            
	            int valueComparison = Integer.compare(value2, value1);

	            if (valueComparison == ExpenseManagementConstants.Integer_0 || (value1 < ExpenseManagementConstants.Integer_0 || value2 < ExpenseManagementConstants.Integer_0)) {
	                return s1.compareTo(s2); // Compare by key
	            }

	            return valueComparison; // Return the value comparison result
	        }
	    };
	}
