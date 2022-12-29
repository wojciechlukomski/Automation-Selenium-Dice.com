package com.dice.base;

import au.com.bytecode.opencsv.CSVReader;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

public class CsvDataProvider {
    // nie zabardzo wiem co tu sie dzieje
    @DataProvider(name = "CsvDataProvider")
    public static Iterator<Object[]> provideData(Method method) {
        List<Object[]> list = new ArrayList<Object[]>();
        String pathname = "src/test/resources/test_data/LoginTest_negativeLoginTest.csv";
        File file = new File(pathname);
        try {
            CSVReader reader = new CSVReader(new FileReader(file));
            String[] keys = reader.readNext();
            if (keys != null) {
                String[] dataParts;
                while ((dataParts = reader.readNext()) != null) {
                    Map<String, String> testData = new HashMap<String, String>();
                    for (int i = 0; i < keys.length; i++) {
                        testData.put(keys[i], dataParts[i]);
                    }
                    list.add(new Object[]{testData});
                }
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return list.iterator();
    }
}

