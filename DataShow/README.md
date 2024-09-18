# README - DataShow Remote Control Application

## Project Overview

The **DataShow Remote Control Application** is a Java-based solution designed to interact with an IR remote control to manage slide shows, mouse movements, and perform tasks like taking screenshots or pressing specific keyboard keys. It decodes IR signals and performs actions like slide navigation, mouse control, and more. The application is particularly useful for managing presentations on embedded devices like Raspberry Pi, connected to projectors or monitors, and can be customized to recognize different remote keys.

It also includes a **Swing-based GUI** for easy configuration of the remote control's buttons, allowing users to map buttons to actions such as starting or stopping the slide show, using a laser pointer, or exiting the presentation.

## Features

### Core Functionality
- **IR Signal Listening**: Detects IR signals using the `irw` command.
- **Customizable Actions**: Configure actions for remote keys, such as moving the mouse, clicking, or navigating a slide show.
- **Robot Class Automation**: Leverages Java’s `Robot` class for automating mouse movement, keyboard input, and screenshots.
  
### GUI-Based Configuration
- **Swing-based Remote Configuration Utility**: Enables easy mapping of remote control buttons to actions via a graphical interface. The configurations are saved to a `remoteConfig.txt` file, which the application references for interpreting remote commands.

## How It Works

### 1. Configuration Setup
The application reads a configuration file (`remoteConfig.txt`) mapping IR key inputs (e.g., `KEY_RIGHT`, `KEY_LEFT`) to corresponding actions (e.g., moving the mouse, pressing arrow keys). 

### 2. Listening for IR Commands
The program listens for key events using the `irw` command. These events are matched with actions defined in the configuration file.

### 3. Performing Actions
When a remote key is pressed, the corresponding action is triggered using the Java `Robot` class, which enables:
- **Mouse Movement**: Move the cursor up, down, left, or right.
- **Keyboard Input**: Simulate key presses (e.g., arrow keys).
- **Mouse Clicks**: Perform left-click or click-and-hold actions.
- **Screenshots**: Capture and save the current screen.

## Configuration File (`remoteConfig.txt`)
This file defines key-action mappings, such as:
```
KEY_RIGHT: MOUSE_RIGHT
KEY_LEFT: MOUSE_LEFT
KEY_2: Print_Screen
```
You can customize this file to adjust the remote control behavior.

## Swing-Based Configuration Utility

The **Swing GUI** provides a graphical interface to modify the remote’s button actions:
1. **Load Configuration**: The GUI reads the `remoteConfig.txt` file to populate comboboxes with current button mappings.
2. **Modify Actions**: Users can select different actions for each remote button using comboboxes.
3. **Save Configuration**: Changes are saved to the configuration file and take effect when the main application is restarted.

### Example Usage:
- Assign “Next Slide” to `KEY_RIGHT` and “Previous Slide” to `KEY_LEFT` in the GUI, and the configuration will update accordingly.

## Setup Instructions

### Prerequisites:
- **Java Development Kit (JDK)** installed on your system.
- **IR sensor** connected to your system (e.g., Raspberry Pi).
- **LIRC (Linux Infrared Remote Control)** installed and configured.

### Running the Application:
1. Clone or download the project files.
2. Make sure the `remoteConfig.txt` file is available.
3. Compile the Java files:
   ```bash
   javac DataShow.java Configuration.java Action.java
   ```
4. Run the main application:
   ```bash
   java DataShow
   ```

### Running the Swing Configuration Utility:
1. Compile and run the `firm1.java`:
   ```bash
   javac firm1.java
   java firm1
   ```
2. Modify button actions via the GUI and save.

## Project Structure

```
/src
│
├── DataShow.java                # Main application
├── remoteConfig.txt             # Configuration file
├── Configuration.java           # Reads and writes configuration mappings
├── Action.java                  # Defines actions like mouse movement or keypresses
├── firm1.java                   # Swing-based configuration GUI
└── SlideShowController.java      # Handles slide show operations
```

## Use Cases

1. **Presentation Control**: Use the remote to navigate slides (`Arrow_Left`, `Arrow_Right`), start/stop the presentation (`F5`), and exit (`ESC`).
2. **Mouse Movements**: Move the mouse cursor using remote keys.
3. **Screen Capture**: Press a remote key to capture a screenshot.

## Example Configuration:
```
KEY_RIGHT: Arrow_Right
KEY_LEFT: Arrow_Left
KEY_UP: Mouse_Up
KEY_DOWN: Mouse_Down
KEY_2: Print_Screen
```

## Extending the Application

To add new actions, extend the `Action` class and implement the `perform()` method. Examples of potential new actions include volume control, media playback, or custom key combinations.

## Troubleshooting

- **No IR Input Detected**: Ensure `lircd` and `irw` are installed and properly configured.
- **Action Not Performed**: Verify key mappings in `remoteConfig.txt`.
- **Robot Class Errors**: Ensure your system allows the `Robot` class to interact with the mouse and keyboard.

## Future Features
- **Macros**: Allow users to assign custom macros to remote buttons.
- **Visual Feedback**: Indicate when a command is received.

This project provides a powerful way to control presentations using an IR remote. Enjoy customizing your remote-controlled experience!