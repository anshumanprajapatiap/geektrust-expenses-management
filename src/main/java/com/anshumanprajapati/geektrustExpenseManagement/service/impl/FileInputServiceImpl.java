package com.anshumanprajapati.geektrustExpenseManagement.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.anshumanprajapati.geektrustExpenseManagement.constant.ExpenseManagementConstants;
import com.anshumanprajapati.geektrustExpenseManagement.service.InputService;


public class FileInputServiceImpl implements InputService{
	
	
	private final File file;
    private BufferedReader reader;

    public FileInputServiceImpl(String filePath) throws FileNotFoundException {
        file = new File(filePath);
        reader = new BufferedReader(new FileReader(file));
    }
    
    @SuppressWarnings(ExpenseManagementConstants.unchecked)
	@Override
	public List<String> getCommandList() {
        List<String> commands = new ArrayList<>();

        try {
            List<String> listOfInputCommand = readAndProcessFileLines();
            commands.addAll(listOfInputCommand);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return commands;
    }
    
    private List<String> readAndProcessFileLines() throws IOException {
        List<String> result = new ArrayList<>();

        String preProcessedInput = processOneLine();
        while (preProcessedInput != null) {
            result.add(preProcessedInput);
            preProcessedInput = processOneLine();
        }

        return result;
    }
    
    private String processOneLine() throws IOException {
    	
    	String line = this.reader.readLine();;
    	if(line == null || isEmpty(line)) {
            return line;
        }

        return line;
       
    }

    private boolean isEmpty(String line) {
        return line==null || line.length()==ExpenseManagementConstants.Integer_0 || line.trim().isEmpty() || line.trim().startsWith(ExpenseManagementConstants.HASH_SYMBOL);
    }

}
