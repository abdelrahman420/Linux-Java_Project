#### Enabling LIRC Support in Yocto for IR Presenter

1. **Introduction:**
   - This document provides a comprehensive guide for enabling Linux Infrared Remote Control (LIRC) in a Yocto image for Raspberry Pi. The configured Yocto image allows users to control presentations on Raspberry Pi via an IR remote and integrates with a Java application to provide a full IR Presenter solution.

2. **Adding LIRC to Yocto Image:**
   - **Step 1: Install the LIRC Package**
     - To include the LIRC package in the Yocto build, add the following lines to your `local.conf` file:
       ```bash
       IMAGE_INSTALL:append = " lirc"
       MACHINE_FEATURES:append = " lirc"
       ```
     - These configurations add LIRC support to the machine configuration, ensuring the necessary LIRC hardware or software requirements are met.

   - **Step 22: Set LIRC Configuration Path**
     - Define the configuration path for LIRC and enable IR functionality by adding these lines in your `local.conf`:
       ```bash
       LIRC_CONF_PATH = "/etc/lirc/lirc.conf"
       ENABLE_IR = "1"
       ```
     - This configuration specifies where the LIRC will look for its configuration files and ensures that IR support is enabled in the system.

3. **Building the Image with LIRC Support:**
   - Once you have configured LIRC support in `local.conf`, proceed to build the Yocto image:
     ```bash
     bitbake presenter-image
     ```
   - Ensure all dependencies are correctly set up and that the image is built without errors.

4. **Troubleshooting LIRC Device Detection:**
   - After the initial build, if LIRC devices such as `lirc0` or `lirc1` are not visible under `/dev`, you may need to add the `opkg` package manager to the image. This is crucial for managing and troubleshooting packages:
     ```bash
     IMAGE_INSTALL:append = " opkg"
     ```
   - With `opkg` installed, you can install additional packages or troubleshoot missing LIRC devices.

5. **Testing IR Remote Control:**
   - To test the IR remote control setup and ensure that the receiver is working, use the `mode2` command to observe IR pulses:
     ```bash
     mode2 -m -d /dev/lirc1
     ```
   - If no output is observed, it might be necessary to compile the `gpio_ir.dts` device tree overlay on the host machine and add it to the Raspberry Pi's boot partition under `overlays`. This step allows the system to recognize and properly configure the IR receiver.

6. **Configuring Remote Control Buttons:**
   - Use the `irrecord` command to configure specific buttons on the remote for actions like scrolling slides, moving left or right, and other custom tasks:
     ```bash
     irrecord -d /dev/lirc1 /etc/lirc/lircd.conf
     ```
   - After recording, ensure that the configuration is correct. You can verify button presses and their mapped actions by testing with the `irw` command:
     ```bash
     irw
     ```
   - If the `irw` command does not produce the expected output, it could indicate issues with the configuration or missing dependencies. Continue troubleshooting or revisit the configuration steps.