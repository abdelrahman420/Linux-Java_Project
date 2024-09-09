package datashow;

import datashow.Configuration;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class DataShow {

    public static void act(String action) {
        Robot robot;
        try {
            robot = new Robot();

            Point currentPosition;
            int currentX, currentY;
            boolean presenting = false;
            boolean holding = false;
            switch (action) {
                case "MOUSE_RIGHT":
                    for (int i = 0; i < 30; i++) {
                        currentPosition = MouseInfo.getPointerInfo().getLocation();
                        currentX = (int) currentPosition.getX();
                        currentY = (int) currentPosition.getY();
                        robot.mouseMove(currentX + 1, currentY);
                    }
                    break;

                case "MOUSE_LEFT":
                    for (int i = 0; i < 30; i++) {
                        currentPosition = MouseInfo.getPointerInfo().getLocation();
                        currentX = (int) currentPosition.getX();
                        currentY = (int) currentPosition.getY();
                        robot.mouseMove(currentX - 1, currentY);
                    }
                    break;

                case "KEY_UP":
                    for (int i = 0; i < 30; i++) {
                        currentPosition = MouseInfo.getPointerInfo().getLocation();
                        currentX = (int) currentPosition.getX();
                        currentY = (int) currentPosition.getY();
                        robot.mouseMove(currentX, currentY - 1);
                    }
                    break;

                case "KEY_DOWN":
                    for (int i = 0; i < 30; i++) {
                        currentPosition = MouseInfo.getPointerInfo().getLocation();
                        currentX = (int) currentPosition.getX();
                        currentY = (int) currentPosition.getY();
                        robot.mouseMove(currentX, currentY + 1);
                    }
                    break;

                case "KEY_8":
                    robot.keyPress(KeyEvent.VK_UP);
                    robot.delay(10);
                    robot.keyRelease(KeyEvent.VK_UP);
                    holding = false;
                    break;

                case "KEY_0":
                    robot.keyPress(KeyEvent.VK_DOWN);
                    robot.delay(10);
                    robot.keyRelease(KeyEvent.VK_DOWN);
                    holding = false;
                    break;

                case "KEY_NEXT":
                    robot.keyPress(KeyEvent.VK_RIGHT);
                    robot.delay(10);
                    robot.keyRelease(KeyEvent.VK_RIGHT);
                    holding = false;
                    break;

                case "KEY_PREVIOUS":
                    robot.keyPress(KeyEvent.VK_LEFT);
                    robot.delay(10);
                    robot.keyRelease(KeyEvent.VK_LEFT);
                    holding = false;
                    break;

                case "KEY_ENTER":
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.delay(5);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    holding = false;
                    break;

                case "KEY_2":
                    System.out.println("KEY_2 was pressed! Taking screenshot...");

                    // Get the screen dimensions for the capture
                    Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

                    // Capture the screen
                    BufferedImage capture = robot.createScreenCapture(screenRect);

                    // Save the screenshot to a file
                    try {
                        ImageIO.write(capture, "png", new File("/home/root/screenshot.png"));
                        System.out.println("Screenshot saved as screenshot.png");
                    } catch (IOException e) {
                        System.out.println("Failed to save screenshot: " + e.getMessage());
                        e.printStackTrace();
                    }
                    holding = false;
                    break;

                case "KEY_1":
                    if (!presenting) {
                        robot.keyPress(KeyEvent.VK_F5);
                        robot.delay(10);
                        robot.keyRelease(KeyEvent.VK_F5);
//                        Thread.sleep(500);
                        presenting = true;
                    } else {
                        robot.keyPress(KeyEvent.VK_ESCAPE);
                        robot.delay(10);
                        robot.keyRelease(KeyEvent.VK_ESCAPE);
//                        Thread.sleep(500);
                        presenting = false;
                    }
                    holding = false;
                    break;

                case "KEY_3":
                    robot.mouseWheel((-5));
                    robot.delay(1000);
                    break;

                case "KEY_6":
                    robot.mouseWheel((5));
                    robot.delay(1000);
                    break;

                case "KEY_5":
                    if (!holding) {
                        robot.mousePress(InputEvent.BUTTON1_MASK);
                        robot.delay(5);

                        holding = true;

                    } else {
                        robot.mouseRelease(InputEvent.BUTTON1_MASK);
                        robot.delay(5);
                        holding = false;
                    }
                    break;

                case "KEY_4":
                    robot.keyPress(KeyEvent.VK_4);
                    robot.delay(10);
                    robot.keyRelease(KeyEvent.VK_4);
                    holding = false;
                    break;

                case "KEY_7":
                    robot.keyPress(KeyEvent.VK_7);
                    robot.delay(10);
                    robot.keyRelease(KeyEvent.VK_7);
                    holding = false;
                    break;

                case "KEY_9":

                    robot.keyPress(KeyEvent.VK_9);
                    robot.delay(10);
                    robot.keyRelease(KeyEvent.VK_9);
                    holding = false;
                    break;

                default:
                    break;
            }

        } catch (AWTException ex) {
            Logger.getLogger(DataShow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.loadFromFile("remoteConfig.txt");  //load key from file
        String LastCommand = "";

        try {
            Process process = new ProcessBuilder("irw").start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            System.out.println("Listening for IR key 44...");
            String action = "";
            while ((line = reader.readLine()) != null) {
                if (!line.equals(LastCommand)) {
                    LastCommand = line;
                    System.out.println(line);
                    //mapping for my configuration
//                    String action = config.getKeyValue(line);
                    //case key not found 
//                    if (action == null) {
//                        action = line; //to prevent null the original key's action would be taken
//                    }
                    if (line.contains("KEY_RIGHT")) {
                        action = config.getKeyValue("KEY_RIGHT");

                        // Mouse movement for KEY_LEFT
                    } else if (line.contains("KEY_LEFT")) {
                        action = config.getKeyValue("KEY_LEFT");

                        // Mouse movement for KEY_UP
                    } else if (line.contains("KEY_UP")) {

                        action = config.getKeyValue("KEY_UP");

                        // Mouse movement for KEY_DOWN
                    } else if (line.contains("KEY_DOWN")) {
                        action = config.getKeyValue("KEY_DOWN");

                    } else if (line.contains("00 KEY_8")) {
                        action = config.getKeyValue("KEY_8");

                    } else if (line.contains("00 KEY_0")) {
                        action = config.getKeyValue("KEY_0");

                    } else if (line.contains("00 KEY_NEXT")) {
                        action = config.getKeyValue("KEY_NEXT");

                    } // Simulate left arrow key press for KEY_PREVIOUS
                    else if (line.contains("00 KEY_PREVIOUS")) {
                        action = config.getKeyValue("KEY_PREVIOUS");

                        // Simulate mouse left-click for KEY_ENTER
                    } else if (line.contains("00 KEY_ENTER")) {
                        action = config.getKeyValue("KEY_ENTER");

                        // **Newly Added Condition: Capture KEY_2 and Take Screenshot**
                    } else if (line.contains("00 KEY_2")) {
                        action = config.getKeyValue("KEY_2");

                    } else if (line.contains("00 KEY_1")) {
                        action = config.getKeyValue("KEY_1");

                    } else if (line.contains("00 KEY_3")) {
                        action = config.getKeyValue("KEY_3");

                    } else if (line.contains("00 KEY_6")) {
                        action = config.getKeyValue("KEY_6");

                    } else if (line.contains("00 KEY_5")) {
                        action = config.getKeyValue("KEY_5");

                    } else if (line.contains("00 KEY_4")) {
                        action = config.getKeyValue("KEY_4");

                    } else if (line.contains("00 KEY_7")) {
                        action = config.getKeyValue("KEY_7");

                    } else if (line.contains("00 KEY_9")) {
                        action = config.getKeyValue("KEY_9");

                    }

                    act(action);

                }
            }
//                Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
