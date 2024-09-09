//package datashow;

//import datashow.Configuration;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DataShow {

    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.loadFromFile("/home/configuration/sudoku.txt");  //load key from file

        String LastCommand = "";

        try {
            Process process = new ProcessBuilder("irw").start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            System.out.println("Listening for IR key 44...");
            Action action = null;

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

                    if (action != null) {
                        action.perform();
                        action = null;
                    }
                }
            }
//                Thread.sleep(3000);

        } catch (IOException e) {
        }
    }
}
