package com.anshumanprajapati.geektrustExpenseManagement.model;

import java.util.List;

import com.anshumanprajapati.geektrustExpenseManagement.enums.OperationEnum;

public class CommandModel {
	
	private OperationEnum inputCommand;
    private List<String> commandParams;

    public CommandModel(OperationEnum inputCommand, List<String> commandParams){
        this.inputCommand = inputCommand;
        this.commandParams = commandParams;
    }

    public OperationEnum getInputCommand() {
        return inputCommand;
    }

    public List<String> getCommandParams() {
        return commandParams;
    }

}
