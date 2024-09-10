package javaapp;

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

    private Map<String, Action> keyValues;

    public Configuration() {
        keyValues = new HashMap<>();
    }

    public void setKeyValue(String key, Action value) {
        keyValues.put(key, value);
    }

    //to take key and return value 
    public Action getKeyValue(String key) {
        return keyValues.get(key);
    }

    public static void setMousePressed(boolean status) {
        mousePressed = status;
    }

    public void writeToFile(String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (Map.Entry<String, Action> entry : keyValues.entrySet()) {
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

                    //setKeyValue(key, value);/************start storing**************/
                    switch (value) {
                        case "MOUSE_RIGHT":
                            Action mouse_right = new Mouse_Right();
                            setKeyValue(key, mouse_right);
                            break;

                        case "MOUSE_LEFT":
                            Action mouse_left = new Mouse_Left();
                            setKeyValue(key, mouse_left);
                            break;

                        case "MOUSE_UP":
                            Action mouse_up = new Mouse_Up();
                            setKeyValue(key, mouse_up);
                            break;

                        case "MOUSE_DOWN":
                            Action mouse_down = new Mouse_Down();
                            setKeyValue(key, mouse_down);
                            break;

                        case "ARROW_UP":
                            Action arrow_up = new Arrow_Up();
                            setKeyValue(key, arrow_up);
                            break;

                        case "ARROW_DOWN":
                            Action arrow_down = new Arrow_Down();
                            setKeyValue(key, arrow_down);
                            break;

                        case "ARROW_RIGHT":
                            Action arrow_right = new Arrow_Right();
                            setKeyValue(key, arrow_right);
                            break;

                        case "ARROW_LEFT":
                            Action arrow_left = new Arrow_Left();
                            setKeyValue(key, arrow_left);
                            break;

                        case "LEFT_CLICK":
                            Action left_click = new Left_Click();
                            setKeyValue(key, left_click);
                            break;

                        case "PRTSC":
                            Action prtsc = new Print_Screen();
                            setKeyValue(key, prtsc);
                            break;

                        case "TOGGLE_PRESENTATION":
                            Action toggle_presentation = new Toggle_Presentation();
                            setKeyValue(key, toggle_presentation);
                            break;

                        case "SCROLL_UP":
                            Action scroll_up = new Scroll_Up();
                            setKeyValue(key, scroll_up);
                            break;

                        case "SCROLL_DOWN":
                            Action scroll_down = new Scroll_Down();
                            setKeyValue(key, scroll_down);
                            break;

                        case "TOGGLE_HOLD":
                            Action toggle_hold = new Toggle_Hold();
                            setKeyValue(key, toggle_hold);
                            break;

                        case "PRESS_0":
                            Action press_0 = new Print_0();
                            setKeyValue(key, press_0);
                            break;

                        case "PRESS_1":
                            Action press_1 = new Print_1();
                            setKeyValue(key, press_1);
                            break;

                        case "PRESS_2":
                            Action press_2 = new Print_2();
                            setKeyValue(key, press_2);
                            break;

                        case "PRESS_3":
                            Action press_3 = new Print_3();
                            setKeyValue(key, press_3);
                            break;

                        case "PRESS_4":
                            Action press_4 = new Print_4();
                            setKeyValue(key, press_4);
                            break;

                        case "PRESS_5":
                            Action press_5 = new Print_5();
                            setKeyValue(key, press_5);
                            break;

                        case "PRESS_6":
                            Action press_6 = new Print_6();
                            setKeyValue(key, press_6);
                            break;

                        case "PRESS_7":
                            Action press_7 = new Print_7();
                            setKeyValue(key, press_7);
                            break;

                        case "PRESS_8":
                            Action press_8 = new Print_8();
                            setKeyValue(key, press_8);
                            break;

                        case "PRESS_9":
                            Action press_9 = new Print_9();
                            setKeyValue(key, press_9);
                            break;

                        case "CLOSE":
                            Action close = new Close();
                            setKeyValue(key, close);
                            break;

                        default:
                            break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from file: " + e.getMessage());
        }
    }
}
