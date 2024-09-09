/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package datashow;

/**
 *
 * @author hp
 */
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Configuration {
    private static boolean mousePressed = false;

    private Map<String, String> keyValues;

    public Configuration() {
        keyValues = new HashMap<>();
    }

    public void setKeyValue(String key, String value) {
        keyValues.put(key, value);
    }

    //to take key and return value 
    public String getKeyValue(String key) {
        return keyValues.get(key);
    }

    public static void setMousePressed(boolean status) {
        mousePressed = status;
    }

    public void writeToFile(String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (Map.Entry<String, String> entry : keyValues.entrySet()) {
                fileWriter.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file: " + e.getMessage());
        }
    }

    public void loadFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                //"KEY: VALUE"
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    setKeyValue(key, value);/************start storing**************/
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from file: " + e.getMessage());
        }
    }
}

