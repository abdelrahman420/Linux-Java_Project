# IR Presenter for Data Show: A Yocto-Based Java Application Project

## Overview

This project focuses on developing a data show controller using an IR remote and IR sensor integrated with a Java application (IR Presenter). The goal is to create a Yocto-based Linux image (Sato with systemd) that supports this functionality. The image is based on the Yocto Kirkstone release, and the entire setup includes IR remote configuration, Java-based application integration, and a custom LibreOffice-based data show application.

## Table of Contents

1. [Project Objective](#project-objective)
2. [Yocto Integration and Image Configuration](#yocto-integration-and-image-configuration)
3. [Java Integration via Meta-Java Layer](#java-integration-via-meta-java-layer)
4. [LIRC Integration for IR Remote Control](#lirc-integration-for-ir-remote-control)
5. [Testing IR Remote with Raspbian](#testing-ir-remote-with-raspbian)
6. [Creating Custom Overlays and DTS Compilation](#creating-custom-overlays-and-dts-compilation)
7. [Developing the Java Application](#developing-the-java-application)
8. [Integrating the Data Show Application](#integrating-the-data-show-application)
9. [Challenges and Solutions](#challenges-and-solutions)
10. [Project Hierarchy](#project-hierarchy)
11. [Images and Examples](#images-and-examples)
12. [Conclusion](#conclusion)

---

## Project Objective

The goal of this project is to create an IR-controlled data show system that can be used to navigate presentation slides. The system involves several components, including hardware (IR remote, IR sensor), software (Java application for controlling the data show), and integration with a Yocto-based Linux system. The project leverages the flexibility and modularity of Yocto to build a custom Linux image tailored for this purpose.

## Yocto Integration and Image Configuration

To begin with, we needed a Linux image that supports a graphical user interface (GUI) and is based on the `systemd` initialization system. We chose the Sato image from the Yocto Project, which is compatible with the Kirkstone release. The first step was to configure Yocto to include the necessary layers and components.

### Steps Taken:

1. **Configure the Build Environment**: Set up the Yocto build environment for the Kirkstone release.
2. **Include Necessary Layers**: Added the `meta-openembedded`, `meta-oe`, and `meta-java` layers to our `bblayers.conf` file:
   ```bash
   BBLAYERS += "path_to_source/meta-openembedded/meta-oe"
   BBLAYERS += "path_to_source/sources/meta-java"
   ```
3. **Configure Local and Distro Files**: Modified `local.conf` and distro configuration files to ensure compatibility with `systemd` and Sato.

## Java Integration via Meta-Java Layer

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

## LIRC Integration for IR Remote Control

To use the IR remote for controlling the data show, we integrated `LIRC` (Linux Infrared Remote Control) into our Yocto image.

### Steps Taken:

1. **Identify the LIRC Package Location**: Found the `lirc` recipe in `meta-openembedded/meta-oe/dynamic-layers/meta-python/recipes-connectivity/lirc`.
2. **Enable LIRC in the Yocto Image**: Added `lirc` to `IMAGE_INSTALL` and configured necessary dependencies.


After resolving this, we were able to see `lirc0` and `lirc1` under `/dev`.

## Testing IR Remote with Raspbian

At this stage, we needed to test the IR remote with the receiver sensor to identify the required configurations. To do this, we used a Raspbian image.

### Steps Taken:

1. **Set Up Raspbian Environment**: Configured the IR remote with specific button mappings to control presentation slides.
2. **Extract Configuration Files**: Once the remote was configured successfully, we extracted the configuration file to replicate it in our Yocto-based image.

## Creating Custom Overlays and DTS Compilation

After testing on Raspbian, we identified that the `gpio_ir.dts` file needed to be compiled on our host machine and added to the boot partition overlays.

### Steps Taken:

1. **Compile DTS File**: Compiled `gpio_ir.dts` on the host machine.
2. **Add to Boot Overlays**: Added the compiled file to the boot partition under the `overlays` directory.

With this setup, we were able to configure the remote using the `irrecord` command on our Sato image.

## Developing the Java Application

The Java application, named "IR Presenter," was developed to interpret the remote signals and perform actions such as scrolling through presentation slides.

### Steps Taken:

1. **Understand IR Signals**: Used `irw` to map remote signals to specific actions.
2. **Develop the Application**: Created a Java application that listens for IR signals and executes corresponding actions.

## Integrating the Data Show Application

For the data show functionality, we decided to integrate a subset of LibreOffice's presentation package.

### Steps Taken:


## Challenges and Solutions

Throughout the project, we faced multiple challenges such as missing devices, `irw` command issues, and branch compatibility problems. Each challenge was addressed by thorough research, testing on alternative platforms, and collaborating as a team to parallelize tasks.

## Project Hierarchy

Below is the hierarchy of the project files and directories:

```
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

```

## Images and Examples

Below are some images and examples to help visualize the project setup:

1. **Yocto Image Build Environment**:
   ![Yocto Build Environment](yocto_build_image.png)

2. **IR Remote Configuration Setup**:
   ![IR Remote Setup](ir_remote_setup_image.png)

3. **Java Application GUI**:
   ![Java Application GUI](java_app_gui_image.png)
