# **IR Presenter for Data Show: A Yocto-Based Java Application Project**

## **Overview**

The **IR Presenter for Data Show** project focuses on developing a data show controller using an IR remote and IR sensor integrated with a Java application (IR Presenter). The goal is to create a Yocto-based Linux image (Sato with systemd) that supports this functionality. The image is based on the Yocto Kirkstone release and includes IR remote configuration, Java-based application integration, and a custom LibreOffice-based data show application.

The primary feature of this project is the ability to control presentation slides via an **Infrared (IR) remote**, enhancing the user experience by providing a simple and reliable way to navigate through presentations without needing to touch the hardware. This project combines the power of **Yocto Project**'s build system with a **Java application** that manages slide presentations.

## **Table of Contents**

1. [Project Objective](#project-objective)
2. [Project Structure](#project-structure)
3. [Yocto Integration and Image Configuration](#yocto-integration-and-image-configuration)
4. [Java Integration via Meta-Java Layer](#java-integration-via-meta-java-layer)
5. [LIRC Integration for IR Remote Control](#lirc-integration-for-ir-remote-control)
6. [Testing IR Remote with Raspbian](#testing-ir-remote-with-raspbian)
7. [Creating Custom Overlays and DTS Compilation](#creating-custom-overlays-and-dts-compilation)
8. [Developing the Java Application](#developing-the-java-application)
9. [Integrating the Data Show Application](#integrating-the-data-show-application)
10. [Challenges and Solutions](#challenges-and-solutions)
11. [Acknowledgements](#acknowledgements)

## **Project Objective**

The goal of this project is to create an IR-controlled data show system that can be used to navigate presentation slides. The system involves several components, including hardware (IR remote, IR sensor), software (Java application for controlling the data show), and integration with a Yocto-based Linux system. The project leverages the flexibility and modularity of Yocto to build a custom Linux image tailored for this purpose.

## **Project Structure**

The project is organized into several key components and directories, each with a specific purpose to ensure modularity, scalability, and ease of integration within the Yocto build system:

1. **yocto/meta-java-integration**:  
   - This layer handles the integration of Java applications into the Yocto build system. It includes all necessary recipes, configurations, and dependencies to ensure that Java applications run smoothly on the custom Yocto image.
   - This component is crucial as the Java application is the backbone of the IR Presenter, processing IR signals and executing corresponding actions. The integration layer manages Java runtime dependencies, ensuring compatibility with the Yocto environment and Sato desktop.

2. **yocto/meta-lirc-integration**:  
   - This layer provides all the necessary configurations to integrate **Linux Infrared Remote Control (LIRC)** into the Yocto image. It includes kernel module configurations, device tree overlays, and LIRC utilities.
   - LIRC integration is essential for receiving IR signals, which are then interpreted by the Java application. This layer ensures the IR receiver and remote work out of the box, providing a plug-and-play experience for end users.

3. **applications/java-app**:  
   - This directory contains the Java source code responsible for slide presentations. The application is designed to handle IR signals from the receiver attached to the Raspberry Pi and map them to specific actions like moving slides back and forth, pausing, or resuming the presentation.
   - It is optimized to run on the Sato desktop environment provided by the Yocto image, and it includes comprehensive error handling, signal processing algorithms, and user-friendly interface elements.

4. **resources**:  
   - This folder contains additional resources such as documentation, diagrams, configuration files, and any other supporting materials required for the project.
   - The resources are designed to assist developers and users in understanding the setup, configuration, and operation of the IR Presenter system. It includes user guides, developer notes, and schematic diagrams for hardware connections.

## **Yocto Integration and Image Configuration**

To begin with, we needed a Linux image that supports a graphical user interface (GUI) and is based on the `systemd` initialization system. We chose the Sato image from the Yocto Project, which is compatible with the Kirkstone release. The first step was to configure Yocto to include the necessary layers and components.

### Steps Taken:

1. **Set Up the Yocto Build Environment**: Clone the necessary Yocto repositories, including `poky`, `meta-raspberrypi`, `meta-openembedded`, `meta-java`, and `meta-lirc`.
2. **Configure Layers and Recipes**: Added the custom layers (`meta-java-integration`, `meta-lirc-integration`, `meta-custom-rpi4`) to the `bblayers.conf` file.
3. **Configure Local and Distro Files**: Modified `local.conf` and distro configuration files to ensure compatibility with `systemd` and Sato.

## **Java Integration via Meta-Java Layer**

Java is central to our project as it is used for developing the IR Presenter application. We integrated Java using the `meta-java` layer provided by the OpenEmbedded community.

### Steps Taken:

1. **Install the Meta-Java Layer**: Clone the `meta-java` repository and add it to the Yocto build environment.
2. **Add Dependencies**: The `meta-java` layer depends on `meta-oe`. We included this layer and ensured it was correctly referenced in `bblayers.conf`.
3. **Configure Java Providers**: Added the following configurations to the `local.conf` or distro include file:
   ```bash
   PREFERRED_PROVIDER_virtual/java-initial-native = "cacao-initial-native"
   PREFERRED_PROVIDER_virtual/java-native = "jamvm-native"
   PREFERRED_PROVIDER_virtual/javac-native = "ecj-bootstrap-native"
   ```

## **LIRC Integration for IR Remote Control**

To use the IR remote for controlling the data show, we integrated `LIRC` (Linux Infrared Remote Control) into our Yocto image.

### Steps Taken:

1. **Identify the LIRC Package Location**: Found the `lirc` recipe in `meta-openembedded/meta-oe/dynamic-layers/meta-python/recipes-connectivity/lirc`.
2. **Enable LIRC in the Yocto Image**: Added `lirc` to `IMAGE_INSTALL` and configured necessary dependencies.


After resolving this, we were able to see `lirc0` and `lirc1` under `/dev`.

## **Testing IR Remote with Raspbian**

At this stage, we needed to test the IR remote with the receiver sensor to identify the required configurations. To do this, we used a Raspbian image.

### Steps Taken:

1. **Set Up Raspbian Environment**: Configured the IR remote with specific button mappings to control presentation slides.
2. **Extract Configuration Files**: Once the remote was configured successfully, we extracted the configuration file to replicate it in our Yocto-based image.

## **Creating Custom Overlays and DTS Compilation**

After testing on Raspbian, we identified that the `gpio_ir.dts` file needed to be compiled on our host machine and added to the boot partition overlays.

### Steps Taken:

1. **Compile DTS File**: Compiled `gpio_ir.dts` on the host machine.
2. **Add to Boot Overlays**: Added the compiled file to the boot partition under the `overlays` directory.

With this setup, we were able to configure the remote using the `irrecord` command on our Sato image.

## **Developing the Java Application**

The Java application, named "IR Presenter," was developed to interpret the remote signals and perform actions such as scrolling through presentation slides.

### Steps Taken:

1. **Understand IR Signals**: Used `irw` to map remote signals to specific actions.
2. **Develop the Application**: Created a Java-based GUI application that integrates with the LIRC system to provide real-time control over presentation slides.

## **Integrating the Data Show Application**

The project includes the integration of a custom LibreOffice-based data show application. This application is configured to work seamlessly with the IR Presenter system.

## **Challenges and Solutions**

- **IR Remote Configuration Issues**: We faced challenges with IR remote device files not appearing under `/dev`. The solution was to install `opkg` and necessary dependencies to ensure proper device recognition.
- **Custom DTS Overlays**: The need to create and compile custom Device Tree Overlays (DTS) was addressed by testing with Raspbian and using extracted configuration files.

## **Acknowledgements**

This project builds on the strong foundation laid by the OpenEmbedded and Yocto Project communities. We extend our gratitude to the

## Project Hierarchy

Below is the hierarchy of the project files and directories:

```
<<<<<<< HEAD
meta_Presenter/
├── conf
│   ├── distro
│   │   └── presenter.conf
│   └── layer.conf
├── COPYING.MIT
├── README
├── recipes-evince
│   └── evince
│       └── evince-pdf.bb
├── recipes-image
│   └── image
│       └── presenter-image.bb
└── recipes-java
    └── java
        └── java_1.0.bb
=======
 ── meta-datashow
    ├── conf
    │   ├── distro
    │   │   └── datashow.conf
    │   └── layer.conf
    ├── COPYING.MIT
    ├── README
    ├── recipes-image
    │   └── image
    │       └── datashow-image.bb
    ├── recipes-ir
    │   ├── ir
    │   │   ├── files
    │   │   │   ├── config.txt
    │   │   │   ├── gpio-ir.dtbo
    │   │   │   ├── lircd.conf
    │   │   │   └── lirc_options.conf
    │   │   └── ir_1.0.bb
    │   ├── lirc
    │       └── lirc_%.bbappend
    │   
    └── recipes-java
        └── java
            └── java_1.0.bb

>>>>>>> origin/Yocto
```

## Images and Examples

Below are some images and examples to help visualize the project setup:

1. **Yocto Image Build Environment**:
   ![Yocto Build Environment](yocto_build_image.png)

2. **IR Remote Configuration Setup**:
   ![IR Remote Setup](ir_remote_setup_image.png)

3. **Java Application GUI**:
   ![Java Application GUI](java_app_gui_image.png)
