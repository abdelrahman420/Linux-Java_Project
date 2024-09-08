
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

public class DataShow {

    public static void main(String[] args) {
        Robot robot;
        Point currentPosition;
        int currentX, currentY;
        boolean presenting = false;
        boolean holding = false;

        try {
            // Run the 'irw' command
            Process process = new ProcessBuilder("irw").start();

            // Capture the output of the 'irw' command
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            robot = new Robot();

            System.out.println("Listening for IR key presses...");

            // Read and print each line (decoded key presses)
            while ((line = reader.readLine()) != null) {
                System.out.println(line);  // Output the decoded key press information

                // Mouse movement for KEY_RIGHT
                if (line.contains("KEY_RIGHT")) {
                    for (int i = 0; i < 10; i++) {
                        currentPosition = MouseInfo.getPointerInfo().getLocation();
                        currentX = (int) currentPosition.getX();
                        currentY = (int) currentPosition.getY();
                        robot.mouseMove(currentX + 1, currentY);
                    }

                    // Mouse movement for KEY_LEFT
                } else if (line.contains("KEY_LEFT")) {
                    for (int i = 0; i < 10; i++) {
                        currentPosition = MouseInfo.getPointerInfo().getLocation();
                        currentX = (int) currentPosition.getX();
                        currentY = (int) currentPosition.getY();
                        robot.mouseMove(currentX - 1, currentY);
                    }

                    // Mouse movement for KEY_UP
                } else if (line.contains("KEY_UP")) {
                    for (int i = 0; i < 10; i++) {
                        currentPosition = MouseInfo.getPointerInfo().getLocation();
                        currentX = (int) currentPosition.getX();
                        currentY = (int) currentPosition.getY();
                        robot.mouseMove(currentX, currentY - 1);
                    }

                    // Mouse movement for KEY_DOWN
                } else if (line.contains("KEY_DOWN")) {
                    for (int i = 0; i < 10; i++) {
                        currentPosition = MouseInfo.getPointerInfo().getLocation();
                        currentX = (int) currentPosition.getX();
                        currentY = (int) currentPosition.getY();
                        robot.mouseMove(currentX, currentY + 1);
                    }

                    // Simulate right arrow key press for KEY_NEXT
                } else if (line.contains("KEY_NEXT")) {
                    robot.keyPress(KeyEvent.VK_RIGHT);
                    robot.delay(10);
                    robot.keyRelease(KeyEvent.VK_RIGHT);
                    robot.delay(1000);

                    // Simulate left arrow key press for KEY_PREVIOUS
                } else if (line.contains("KEY_PREVIOUS")) {
                    robot.keyPress(KeyEvent.VK_LEFT);
                    robot.delay(10);
                    robot.keyRelease(KeyEvent.VK_LEFT);
                    Thread.sleep(500);

                    // Simulate mouse left-click for KEY_ENTER
                } else if (line.contains("KEY_ENTER")) {
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.delay(5);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    Thread.sleep(500);

                    // **Newly Added Condition: Capture KEY_5 and Take Screenshot**
                } else if (line.contains("KEY_5")) {
                    System.out.println("KEY_5 was pressed! Taking screenshot...");

                    // Get the screen dimensions for the capture
                    Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

                    // Capture the screen
                    BufferedImage capture = robot.createScreenCapture(screenRect);

                    // Save the screenshot to a file
                    try {
                        ImageIO.write(capture, "png", new File("~/screenshot.png"));
                        System.out.println("Screenshot saved as screenshot.png");
                    } catch (IOException e) {
                        System.out.println("Failed to save screenshot: " + e.getMessage());
                        e.printStackTrace();
                    }
                } else if (line.contains("KEY_0")) {
                    System.out.println("KEY_0 was pressed! Taking screenshot...");
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

                } else if (line.contains("KEY_3")) {
                    robot.mouseWheel((-10));
//                    robot.delay(5);
                } else if (line.contains("KEY_6")) {
                    robot.mouseWheel((10));
//                    robot.delay(5);
                } else if (line.contains("KEY_8")) {
                    if (!holding) {
                        robot.mousePress(InputEvent.BUTTON1_MASK);
                        robot.delay(5);
                        holding = true;

                    } else {
                        robot.mouseRelease(InputEvent.BUTTON1_MASK);
                        robot.delay(5);
                        holding = false;
                    }
                } else if (line.contains("KEY_1")) {
                    robot.keyPress(KeyEvent.VK_RIGHT);
                    robot.delay(10);
                    robot.keyRelease(KeyEvent.VK_RIGHT);
                    robot.delay(1000);
                } else if (line.contains("KEY_2")) {
                    robot.keyPress(KeyEvent.VK_RIGHT);
                    robot.delay(10);
                    robot.keyRelease(KeyEvent.VK_RIGHT);
                    robot.delay(1000);
                } else if (line.contains("KEY_4")) {
                    robot.keyPress(KeyEvent.VK_RIGHT);
                    robot.delay(10);
                    robot.keyRelease(KeyEvent.VK_RIGHT);
                    robot.delay(1000);
                } else if (line.contains("KEY_7")) {
                    robot.keyPress(KeyEvent.VK_RIGHT);
                    robot.delay(10);
                    robot.keyRelease(KeyEvent.VK_RIGHT);
                    robot.delay(1000);
                } else if (line.contains("KEY_9")) {
                    robot.keyPress(KeyEvent.VK_RIGHT);
                    robot.delay(10);
                    robot.keyRelease(KeyEvent.VK_RIGHT);
                    robot.delay(1000);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
