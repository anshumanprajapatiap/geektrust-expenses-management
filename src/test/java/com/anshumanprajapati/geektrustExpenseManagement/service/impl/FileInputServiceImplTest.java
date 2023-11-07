package com.anshumanprajapati.geektrustExpenseManagement.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileInputServiceImplTest {

    private FileInputServiceImpl inputService;

    @TempDir
    File tempDirectory;

    @BeforeEach
    public void setUp() throws IOException {
        File inputFile = new File(tempDirectory, "input.txt");
        FileWriter fileWriter = new FileWriter(inputFile);
        fileWriter.write("MOVE_IN Alice\n");
        fileWriter.write("SPEND 100 Alice Bob\n");
        fileWriter.write("DUES Alice\n");
        fileWriter.close();

        inputService = new FileInputServiceImpl(inputFile.getPath());
    }

    @Test
    public void testGetCommandList() throws IOException {
        List<String> commands = inputService.getCommandList();

        assertEquals(3, commands.size());
        assertEquals("MOVE_IN Alice", commands.get(0));
        assertEquals("SPEND 100 Alice Bob", commands.get(1));
        assertEquals("DUES Alice", commands.get(2));
    }
}
