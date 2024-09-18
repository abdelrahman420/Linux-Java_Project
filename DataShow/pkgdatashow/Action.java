package pkgdatashow;

import java.awt.Point;
import java.awt.Robot;
import java.awt.AWTException;
import java.util.logging.Logger;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import javax.imageio.ImageIO;

abstract public class Action {

    Robot robot;

    Point currentPosition;
    int currentX, currentY;
    boolean presenting = false;
    boolean holding = false;

    Action() {
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(DataShow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    abstract public void perform();
}

class Mouse_Right extends Action {

    @Override
    public void perform() {
        for (int i = 0; i < 30; i++) {
            currentPosition = MouseInfo.getPointerInfo().getLocation();
            currentX = (int) currentPosition.getX();
            currentY = (int) currentPosition.getY();
            robot.mouseMove(currentX + 1, currentY);
        }
    }
}

class Mouse_Left extends Action {

    @Override
    public void perform() {
        for (int i = 0; i < 30; i++) {
            currentPosition = MouseInfo.getPointerInfo().getLocation();
            currentX = (int) currentPosition.getX();
            currentY = (int) currentPosition.getY();
            robot.mouseMove(currentX - 1, currentY);
        }
    }
}

class Mouse_Up extends Action {

    @Override
    public void perform() {
        for (int i = 0; i < 30; i++) {
            currentPosition = MouseInfo.getPointerInfo().getLocation();
            currentX = (int) currentPosition.getX();
            currentY = (int) currentPosition.getY();
            robot.mouseMove(currentX, currentY - 1);
        }
    }
}

class Mouse_Down extends Action {

    @Override
    public void perform() {
        for (int i = 0; i < 30; i++) {
            currentPosition = MouseInfo.getPointerInfo().getLocation();
            currentX = (int) currentPosition.getX();
            currentY = (int) currentPosition.getY();
            robot.mouseMove(currentX, currentY + 1);
        }
    }
}

class Arrow_Right extends Action {

    @Override
    public void perform() {
        robot.keyPress(KeyEvent.VK_RIGHT);
        robot.delay(10);
        robot.keyRelease(KeyEvent.VK_RIGHT);
        holding = false;
    }
}

class Arrow_Left extends Action {

    @Override
    public void perform() {
        robot.keyPress(KeyEvent.VK_LEFT);
        robot.delay(10);
        robot.keyRelease(KeyEvent.VK_LEFT);
        holding = false;
    }
}

class Arrow_Down extends Action {

    @Override
    public void perform() {
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.delay(10);
        robot.keyRelease(KeyEvent.VK_DOWN);
        holding = false;
    }
}

class Arrow_Up extends Action {

    @Override
    public void perform() {
        robot.keyPress(KeyEvent.VK_UP);
        robot.delay(10);
        robot.keyRelease(KeyEvent.VK_UP);
        holding = false;
    }
}

class Toggle_Presentation extends Action {

    @Override
    public void perform() {
        if (!presenting) {
            robot.keyPress(KeyEvent.VK_F5);
            robot.delay(10);
            robot.keyRelease(KeyEvent.VK_F5);

            presenting = true;
        } else {
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.delay(10);
            robot.keyRelease(KeyEvent.VK_ESCAPE);

            presenting = false;
        }
        holding = false;

    }
}

class Left_Click extends Action {

    @Override
    public void perform() {
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(5);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        holding = false;
    }
}

class Scroll_Up extends Action {

    @Override
    public void perform() {
        robot.mouseWheel((-5));
        robot.delay(1000);
    }
}

class Scroll_Down extends Action {

    @Override
    public void perform() {
        robot.mouseWheel((5));
        robot.delay(1000);
    }
}

class Toggle_Hold extends Action {

    @Override
    public void perform() {
        if (!holding) {
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.delay(5);
            holding = true;

        } else {
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            robot.delay(5);
            holding = false;
        }
    }
}

class Print_Screen extends Action {

    @Override
    public void perform() {
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
        }
        holding = false;
    }
}

class Print_0 extends Action {

    @Override
    public void perform() {
        robot.keyPress(KeyEvent.VK_0);
        robot.delay(10);
        robot.keyRelease(KeyEvent.VK_0);
        holding = false;
    }
}

class Print_1 extends Action {

    @Override
    public void perform() {
        robot.keyPress(KeyEvent.VK_1);
        robot.delay(10);
        robot.keyRelease(KeyEvent.VK_1);
        holding = false;
    }
}

class Print_2 extends Action {

    @Override
    public void perform() {
        robot.keyPress(KeyEvent.VK_2);
        robot.delay(10);
        robot.keyRelease(KeyEvent.VK_2);
        holding = false;

    }
}

class Print_3 extends Action {

    @Override

    public void perform() {
        robot.keyPress(KeyEvent.VK_3);
        robot.delay(10);
        robot.keyRelease(KeyEvent.VK_3);
        holding = false;

    }
}

class Print_4 extends Action {

    @Override

    public void perform() {
        robot.keyPress(KeyEvent.VK_4);
        robot.delay(10);
        robot.keyRelease(KeyEvent.VK_4);
        holding = false;

    }
}

class Print_5 extends Action {

    @Override

    public void perform() {

        robot.keyPress(KeyEvent.VK_5);
        robot.delay(10);
        robot.keyRelease(KeyEvent.VK_5);
        holding = false;
    }
}

class Print_6 extends Action {

    @Override

    public void perform() {

        robot.keyPress(KeyEvent.VK_6);
        robot.delay(10);
        robot.keyRelease(KeyEvent.VK_6);
        holding = false;
    }
}

class Print_7 extends Action {

    @Override

    public void perform() {

        robot.keyPress(KeyEvent.VK_7);
        robot.delay(10);
        robot.keyRelease(KeyEvent.VK_7);
        holding = false;
    }
}

class Print_8 extends Action {

    @Override

    public void perform() {

        robot.keyPress(KeyEvent.VK_8);
        robot.delay(10);
        robot.keyRelease(KeyEvent.VK_8);
        holding = false;
    }
}

class Print_9 extends Action {

    @Override
    public void perform() {
        robot.keyPress(KeyEvent.VK_9);
        robot.delay(10);
        robot.keyRelease(KeyEvent.VK_9);
        holding = false;
    }
}
