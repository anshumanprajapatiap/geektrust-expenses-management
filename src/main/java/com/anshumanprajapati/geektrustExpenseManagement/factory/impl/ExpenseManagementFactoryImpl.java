package com.anshumanprajapati.geektrustExpenseManagement.factory.impl;

import com.anshumanprajapati.geektrustExpenseManagement.model.CommandModel;
import com.anshumanprajapati.geektrustExpenseManagement.service.ExpenseManagementService;
import com.anshumanprajapati.geektrustExpenseManagement.service.impl.ClearDueServiceImpl;
import com.anshumanprajapati.geektrustExpenseManagement.service.impl.DuesServiceImpl;
import com.anshumanprajapati.geektrustExpenseManagement.service.impl.MoveInServiceImpl;
import com.anshumanprajapati.geektrustExpenseManagement.service.impl.MoveOutServiceImpl;
import com.anshumanprajapati.geektrustExpenseManagement.service.impl.SpendServiceImpl;

public class ExpenseManagementFactoryImpl {
	
	public ExpenseManagementService getExecutor(CommandModel command) {
		ExpenseManagementService expenseManagementServiceObj = null;
        if(command!=null){
            switch(command.getInputCommand()){
            	case MOVE_IN:
            		expenseManagementServiceObj = new MoveInServiceImpl();
            		break;
            	case SPEND:
            		expenseManagementServiceObj = new SpendServiceImpl();
            		break;
            	case DUES:
            		expenseManagementServiceObj = new DuesServiceImpl();
            		break;
            	case CLEAR_DUE:
            		expenseManagementServiceObj = new ClearDueServiceImpl();
            		break;
            	case MOVE_OUT:
            		expenseManagementServiceObj = new MoveOutServiceImpl();
                default:
                    break;
            }
        }
        return expenseManagementServiceObj;
    }
	

}
