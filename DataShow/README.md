# Swing JavaApp

## Overview
This Java application is designed to load key configurations from a file, display the configuration options in combo boxes, and allow users to modify and update them. The configurations are stored in a key-value pair format and can be loaded from or saved to a file. 

## Features
- **Load Configurations from File**: The application reads key-value pairs from a file (`remoteConfig.txt`) and loads them into combo boxes for user interaction.
- **Update Configuration in Real-time**: Users can select new values from the combo boxes, which are updated in the configuration.
- **Save Configurations to File**: After modifications, the updated configurations can be saved back to the file.
- **Mouse Status Handling**: Includes functionality for tracking and modifying the mouse press status (used in combo box interaction).

## Key Components

1. **ConfigurationChange Class**: 
   - Manages key-value pairs for the configuration.
   - Provides methods for loading configurations from a file and saving them.
   - Updates combo boxes based on the loaded configuration.

2. **firm1 Class**:
   - A GUI class extending `JFrame`.
   - Displays combo boxes representing different keys (e.g., arrows, numbers, special keys).
   - Initializes the GUI components and loads configurations when the window is created.

## File Structure

- `ConfigurationChange.java`: Handles reading and writing configuration files and updating combo boxes.
- `firm1.java`: Manages the graphical interface using Swing components and invokes `ConfigurationChange` methods to manage the configurations.

## Setup and Usage

1. **Loading Configuration**:
   The configuration file should be placed at `C:\\Users\\minam\\OneDrive\\Documents\\NetBeansProjects\\JavaApp\\src\\javaapp\\remoteConfig.txt`. The format of the file is as follows:

   ```
   KEY: VALUE
   ```

   Each line should contain a key-value pair separated by a colon.

2. **Running the Application**:
   - Upon starting the application, the combo boxes will be populated with the values from the configuration file.
   - You can change any value by selecting a different option from the combo boxes.
   
3. **Saving Configuration**:
   - After making the desired changes, the application will automatically save the new configuration back to the file when necessary.

## Example Configuration

```text
Key1: ARROW_UP
Key2: ARROW_DOWN
Key3: LEFT_CLICK
Key4: TOGGLE_PRESENTATION
```

## Dependencies
- **Java SE**: The application runs on standard Java SE and uses the Swing library for the GUI components.
- **NetBeans IDE**: The project is developed using NetBeans. The IDE's form editor is used to design the user interface.

