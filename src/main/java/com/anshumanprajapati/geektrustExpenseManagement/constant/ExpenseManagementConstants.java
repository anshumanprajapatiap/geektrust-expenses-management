package com.anshumanprajapati.geektrustExpenseManagement.constant;

public class ExpenseManagementConstants {
	
	public static final String space = " ";
	public static final String emptyString = "";
	public static final String INPUT_DATA_ERROR = "INPUT_DATA_ERROR";
	public static final String SUCCESS = "SUCCESS";
	public static final String INCORRECT_PAYMENT = "INCORRECT_PAYMENT";
	public static final String MEMBER_NOT_FOUND = "MEMBER_NOT_FOUND";
	public static final String INPUT_AMOUNT_ERROR = "INPUT_AMOUNT_ERROR";
	public static final String HOUSEFUL = "HOUSEFUL";
	public static final String FAILURE = "FAILURE";
	public static final String FileNotFound = "File Not Found";
	
	public static final String minArgs = "minArgs";
	public static final String maxArgs = "maxArgs";
	public static final String unchecked = "unchecked";
	public static final String HASH_SYMBOL = "#";
	
	public static final boolean FALSE = false;
	public static final boolean TRUE = true;
	
	public static final int MaxNumberOFMember = 3;
	
	public static final int Integer_0 = 0;
	public static final int Integer_1 = 1;
	public static final int Integer_2 = 2;
	public static final int Integer_3 = 3;
	public static final int Integer_4 = 4;
	public static final int Integer_5 = 5;
	
	
	
	public static String getOperationValue(String input) {
        String value = emptyString;
        switch (input) {
            default:
                value = input;
                break;
        }
        return value;
    }

}
