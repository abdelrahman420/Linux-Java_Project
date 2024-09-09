
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
import javax.swing.*;

public class DataShow {

    public static void main(String[] args) {

        Robot robot;
        Point currentPosition;
        int currentX, currentY;
        boolean presenting = false;
        boolean holding = false;
        String LastCommand = "";

        try {
            // Run the 'irw' command
            Process process = new ProcessBuilder("irw").start();

            // Capture the output of the 'irw' command
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            robot = new Robot();

            System.out.println("Listening for IR key 44...");

            // Read and print each line (decoded key presses)
            while ((line = reader.readLine()) != null) {
                if (!line.equals(LastCommand)) {
                    LastCommand = line;
                    System.out.println(line);  // Output the decoded key press information
                    if (line.contains("KEY_RIGHT")) {
                        for (int i = 0; i < 30; i++) {
                            currentPosition = MouseInfo.getPointerInfo().getLocation();
                            currentX = (int) currentPosition.getX();
                            currentY = (int) currentPosition.getY();
                            robot.mouseMove(currentX + 1, currentY);
                        }

                        // Mouse movement for KEY_LEFT
                    } else if (line.contains("KEY_LEFT")) {
                        for (int i = 0; i < 30; i++) {
                            currentPosition = MouseInfo.getPointerInfo().getLocation();
                            currentX = (int) currentPosition.getX();
                            currentY = (int) currentPosition.getY();
                            robot.mouseMove(currentX - 1, currentY);
                        }

                        // Mouse movement for KEY_UP
                    } else if (line.contains("KEY_UP")) {
                        for (int i = 0; i < 30; i++) {
                            currentPosition = MouseInfo.getPointerInfo().getLocation();
                            currentX = (int) currentPosition.getX();
                            currentY = (int) currentPosition.getY();
                            robot.mouseMove(currentX, currentY - 1);
                        }

                        // Mouse movement for KEY_DOWN
                    } else if (line.contains("KEY_DOWN")) {
                        for (int i = 0; i < 30; i++) {
                            currentPosition = MouseInfo.getPointerInfo().getLocation();
                            currentX = (int) currentPosition.getX();
                            currentY = (int) currentPosition.getY();
                            robot.mouseMove(currentX, currentY + 1);
                        }
                    }  else if (line.contains("00 KEY_8")) {
                        robot.keyPress(KeyEvent.VK_UP);
                        robot.delay(10);
                        robot.keyRelease(KeyEvent.VK_UP);
                        holding = false;

                    } else if (line.contains("00 KEY_0")) {
                        robot.keyPress(KeyEvent.VK_DOWN);
                        robot.delay(10);
                        robot.keyRelease(KeyEvent.VK_DOWN);
                        holding = false;

                    }
                    else if (line.contains("00 KEY_NEXT")) {
                        robot.keyPress(KeyEvent.VK_RIGHT);
                        robot.delay(10);
                        robot.keyRelease(KeyEvent.VK_RIGHT);
                        holding = false;

                    }
                    // Simulate left arrow key press for KEY_PREVIOUS
                    else if (line.contains("00 KEY_PREVIOUS")) {
                        robot.keyPress(KeyEvent.VK_LEFT);
                        robot.delay(10);
                        robot.keyRelease(KeyEvent.VK_LEFT);
                        holding = false;

                        // Simulate mouse left-click for KEY_ENTER
                    } else if (line.contains("00 KEY_ENTER")) {
                        robot.mousePress(InputEvent.BUTTON1_MASK);
                        robot.delay(5);
                        robot.mouseRelease(InputEvent.BUTTON1_MASK);
                        holding = false;

                        // **Newly Added Condition: Capture KEY_5 and Take Screenshot**
                    } else if (line.contains("00 KEY_2")) {
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

                    } else if (line.contains("00 KEY_1")) {
                        if (!presenting) {
                            robot.keyPress(KeyEvent.VK_F5);
                            robot.delay(10);
                            robot.keyRelease(KeyEvent.VK_F5);
                            Thread.sleep(500);
                            presenting = true;
                        } else {
                            robot.keyPress(KeyEvent.VK_ESCAPE);
                            robot.delay(10);
                            robot.keyRelease(KeyEvent.VK_ESCAPE);
                            Thread.sleep(500);
                            presenting = false;
                        }
                        holding = false;

                    } else if (line.contains("00 KEY_3")) {
                        robot.mouseWheel((-5));
                        robot.delay(1000);

                    } else if (line.contains("00 KEY_6")) {
                        robot.mouseWheel((5));
                        robot.delay(1000);

                    } else if (line.contains("00 KEY_5")) {
                        if (!holding) {
                            robot.mousePress(InputEvent.BUTTON1_MASK);
                            robot.delay(5);

                            holding = true;

                        } else {
                            robot.mouseRelease(InputEvent.BUTTON1_MASK);
                            robot.delay(5);
                            holding = false;
                        }

                    } else if (line.contains("00 KEY_2")) {
                        robot.keyPress(KeyEvent.VK_2);
                        robot.delay(10);
                        robot.keyRelease(KeyEvent.VK_2);

                        holding = false;

                    } else if (line.contains("00 KEY_4")) {
                        robot.keyPress(KeyEvent.VK_4);
                        robot.delay(10);
                        robot.keyRelease(KeyEvent.VK_4);
                        holding = false;

                    } else if (line.contains("00 KEY_7")) {
                        robot.keyPress(KeyEvent.VK_7);
                        robot.delay(10);
                        robot.keyRelease(KeyEvent.VK_7);
                        holding = false;

                    } else if (line.contains("00 KEY_9")) {
                        robot.keyPress(KeyEvent.VK_9);
                        robot.delay(10);
                        robot.keyRelease(KeyEvent.VK_9);

                        holding = false;
                    }
                }
            }
//                Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
