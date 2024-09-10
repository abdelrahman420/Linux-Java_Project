# **Java Integration for IR Presenter on Yocto Project**

## **Overview**

This document provides detailed instructions for integrating Java into a Yocto Project-based Linux image. The Java integration is essential for running the **IR Presenter** application, which processes Infrared (IR) remote signals to control a data show presentation.

The integration uses the `meta-java` layer from the Yocto Project, specifically the **Kirkstone** release, to provide a Java runtime environment. This README will guide you through setting up the `meta-java` layer, configuring your Yocto environment, and deploying a Java application on the custom Yocto image.

## **Table of Contents**

1. [Introduction](#introduction)
2. [Prerequisites](#prerequisites)
3. [Setting Up Yocto Environment](#setting-up-yocto-environment)
4. [Adding Meta-Java Layer](#adding-meta-java-layer)
5. [Configuring Java Providers](#configuring-java-providers)
6. [Building the Yocto Image with Java Support](#building-the-yocto-image-with-java-support)
7. [Deploying the Java Application](#deploying-the-java-application)
8. [Testing the Java Application](#testing-the-java-application)

## **Introduction**

The **IR Presenter** application is a Java-based program that runs on a Yocto-built Linux image for the Raspberry Pi 4 platform. It interprets IR remote signals to control presentation slides and integrates seamlessly with the **LIRC** (Linux Infrared Remote Control) system.

To enable this functionality, we must integrate Java into our Yocto build. We use the `meta-java` layer to provide the necessary Java runtime and dependencies.

## **Prerequisites**

Before proceeding with the integration, ensure you have the following prerequisites:

- A **Yocto Project** environment set up with the **Kirkstone** release.
- Knowledge of Yocto Project basics, including `bitbake`, layers, and recipes.
- Required Yocto layers: `poky`, `meta-raspberrypi`, `meta-openembedded`, `meta-oe`.
- Git installed on the host machine.

## **Setting Up Yocto Environment**

To set up the environment, follow these steps:

1. **Clone the Yocto Project Repository:**
   ```bash
   git clone git://git.yoctoproject.org/poky -b kirkstone
   cd poky
   ```

2. **Source the Environment Script:**
   ```bash
   source oe-init-build-env
   ```

3. **Add Additional Layers:**
   Clone the `meta-raspberrypi` and `meta-openembedded` layers and add them to `bblayers.conf`:
   ```bash
   git clone git://git.openembedded.org/meta-openembedded
   git clone git://git.yoctoproject.org/meta-raspberrypi
   ```

4. **Add Layers to `bblayers.conf`:**
   Open `conf/bblayers.conf` and add the new layers:
   ```bash
   BBLAYERS += "path/to/meta-raspberrypi"
   BBLAYERS += "path/to/meta-openembedded/meta-oe"
   ```

## **Adding Meta-Java Layer**

To integrate Java, the `meta-java` layer must be included in the Yocto environment:

1. **Clone the Meta-Java Layer:**
   ```bash
   git clone https://github.com/openembedded/meta-java.git
   ```

2. **Add Meta-Java Layer to `bblayers.conf`:**
   Update `conf/bblayers.conf` to include the `meta-java` layer:
   ```bash
   BBLAYERS += "path/to/meta-java"
   ```

3. **Add Dependencies for Meta-Java:**
   Ensure the `meta-openembedded` layer is included because `meta-java` depends on it.

## **Configuring Java Providers**

Configuring the Java providers is crucial to ensure that the Yocto build system uses the correct Java runtime and compiler:

  **Add the Following Configuration to `local.conf`:**
   Open `conf/local.conf` and add the following lines:
   ```bash
   PREFERRED_PROVIDER_virtual/java-initial-native = "cacao-initial-native"
   PREFERRED_PROVIDER_virtual/java-native = "jamvm-native"
   PREFERRED_PROVIDER_virtual/javac-native = "ecj-bootstrap-native"
   ```

## **Building the Yocto Image with Java Support**

Once the environment is configured, proceed to build the image:

1. **Add Java Runtime to the Image:**
   In `conf/local.conf`, add the Java runtime to the `IMAGE_INSTALL` list:
   ```bash
   IMAGE_INSTALL:append = " openjdk-8"
   ```

2. **Build the Yocto Image:**
   Run the following command to build the image:
   ```bash
   bitbake datashow-image
   ```

This command builds the `datashow-image` with Java runtime support. The build process may take some time, depending on your machine's capabilities.

## **Deploying the Java Application**

After successfully building the image, deploy the Java application:

1. **Prepare the Java Application:**
   Ensure the `IR Presenter` Java application is built and packaged. The application should be in a JAR format (e.g., `IRPresenter.jar`).

2. **Copy the Java Application to the Target Device:**
   Use `scp` or another method to copy the JAR file to the target Raspberry Pi 4 device running the Yocto image:
   ```bash
   scp IRPresenter.jar root@<device-ip>:/home/root/
   ```

3. **Run the Java Application:**
   SSH into the device and run the Java application:
   ```bash
   ssh root@<device-ip>
   java -jar /home/root/IRPresenter.jar
   ```

## **Testing the Java Application**

After deployment, perform the following tests to ensure the Java application is working correctly:

1. **Verify Java Installation:**
   Run `java -version` or `javac` on the target device to verify that Java is installed correctly.

2. **Test IR Remote Functionality:**
   Use the IR remote to send commands to the Java application. Ensure that the commands are interpreted correctly, and the expected actions (e.g., moving presentation slides) are performed.

