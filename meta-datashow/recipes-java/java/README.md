
# Datashow Java Application Recipe

## Overview

This Yocto recipe installs the Datashow Java application (`datashow.jar`) along with OpenJDK 8 as a dependency. It places the JAR file in `/usr/bin` and provides a wrapper script that allows you to easily run the application by typing `datashow` from the command line.

## Features

- **Java Runtime Environment (JRE) Installation**: The recipe ensures that OpenJDK 8 is available on the system to execute Java applications.
- **Datashow Application**: The Java application is packaged as a JAR file and installed in `/usr/bin` for easy execution.
- **Wrapper Script**: A script is installed that allows the user to run the Java application by simply typing `datashow` without specifying the full Java command.

## Recipe Contents

- **`java.0.bb`**: The main BitBake recipe for installing the `datashow.jar` application and configuring the wrapper script.
- **`datashow.jar`**: The Java application file that will be executed by the system.

## Installation Structure

The recipe installs the following files on the system:

- **`/usr/bin/datashow.jar`**: The JAR file for the Datashow application.
- **`/usr/bin/datashow`**: A shell script that wraps around the `java -jar` command for easy execution of the JAR file.

## Prerequisites

- **Yocto/OpenEmbedded Environment**: This recipe is meant to be used within a Yocto or OpenEmbedded build environment.
- **Java Runtime**: OpenJDK 8 is a runtime dependency of this recipe. The recipe ensures that the JRE is installed as part of the build process.

## Instructions

### 1. Adding the Recipe to Your Yocto Build

To include the Datashow Java application in your Yocto image:

- Place the `java.0.bb` recipe in your Yocto layer under `recipes-datashow/java/`.
- Place the `datashow.jar` file in the same directory under `recipes-datashow/java/files/`.

Example structure:
```
datashow-java/
├── java/
│   ├── files/
│   │   └── datashow.jar
│   └── java.0.bb
```

### 2. Modify Your Build Configuration

Add the Datashow application to your image by including the following line in your `local.conf` or your custom image recipe:

```bash
IMAGE_INSTALL:append = " java"
```

This ensures that the `java` application is included in your final image.

### 3. Building the Image

Run the Yocto build command to compile the image and include the Datashow application:

```bash
bitbake datasashow-image
```

Once the build is complete, the `datashow` application will be included in the image, along with the necessary Java runtime environment.

### 4. Running the Application

After deploying the image to your target device, you can run the Datashow application by simply typing:

```bash
datashow
```

This will execute the `datashow.jar` Java application.

## Customization

- **Changing the JAR File Path**: If you want to install the `datashow.jar` file to a different location, modify the `DEST_DIR` variable in the recipe:
  ```bash
  DEST_DIR = "/path/to/your/directory"
  ```
  
- **Wrapper Script**: The wrapper script is installed in `/usr/bin` and allows you to run the application by typing `datashow`. You can change the script or its location by modifying the `do_install` function in the recipe.

## Recipe Breakdown

- **`SRC_URI`**: Points to the location of the `datashow.jar` file.
- **`do_install()`**: This function installs the JAR file to `/usr/bin` and creates a wrapper script (`datashow`) to run the application.
- **`RDEPENDS_${PN}`**: Specifies that OpenJDK 8 should be installed as a runtime dependency.

## Example

The wrapper script in `/usr/bin/datashow` contains the following:
```bash
#!/bin/sh
exec java -jar /usr/bin/datashow.jar "$@"
```

This ensures that you can run the application with the command:
```bash
datashow
```

