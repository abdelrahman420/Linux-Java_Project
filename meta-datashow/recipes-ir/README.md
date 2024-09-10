# Linux Infrared Remote Control (LIRC) Recipe


## Recipe Structure
The recipe installs necessary components, such as the `lircd.conf` configuration file and device tree overlay, to enable LIRC support on the system. It also ensures that required kernel modules are loaded automatically during boot. Below is a detailed breakdown of the recipe.

---

### **Recipe Contents**

#### 1. `ir_1.0.bb`
This is the main BitBake recipe that installs and configures LIRC. It ensures that all necessary files are included in the final image and defines the tasks performed during installation.

#### 2. `lirc_%.bbappend`
This file appends the `lirc` package installation and other configurations to ensure proper LIRC support on the image.

#### 3. **Files Folder**
The `files` folder contains important configuration files and device tree overlays required for LIRC to function:
- **`config.txt`**: Boot configuration file that ensures proper initialization of infrared hardware.
- **`gpio-ir.dtbo`**: Device tree overlay that configures the GPIO pins for infrared reception.
- **`lircd.conf`**: LIRC configuration file that defines the mapping of remote control button signals to specific events.

---

### **Key Variables**
- **`SUMMARY`**: Provides a brief description of the recipe's purpose.
- **`DESCRIPTION`**: Offers a more detailed explanation of the recipe.
- **`LICENSE`**: Set to `"CLOSED"` to indicate a custom or proprietary license.
- **`IMAGE_INSTALL:append`**: Ensures that the `lirc` package is included in the image.
- **`MACHINE_FEATURES:append`**: Adds LIRC support as a machine feature.
- **`KERNEL_MODULE_AUTOLOAD`**: Automatically loads the necessary kernel modules (`lirc_dev`, `lirc_rpi`) on boot.
- **`LIRC_CONF_PATH`**: Custom variable to define the LIRC configuration path (currently unused in the recipe).
- **`OVERLAY_PATH`**: Defines the location for device tree overlays.
- **`ENABLE_IR`**: Custom variable to enable infrared (currently unused in the recipe).
- **`FILES_${PN}`**: Specifies the files that are included in the final package.
- **`SRC_URI:append`**: Defines the location of source files, including `lircd.conf`, `gpio-ir.dtbo`, and `config.txt`.

---

### **Installation Process**
The recipe defines the installation process using the `do_install` function, which handles the following steps:

1. **Create Directories**:
   - `/etc/lirc` for LIRC configuration files.
   - `/boot/overlays` for device tree overlays.
   - `/boot` for boot-related configurations.

2. **Install Configuration Files**:
   - Copies `lircd.conf` to `/etc/lirc`.
   - Installs the `gpio-ir.dtbo` overlay in `/boot/overlays`.
   - Installs the `config.txt` boot configuration file to `/boot`.

---

### **Usage**
To use this recipe:
1. **Add to Your Yocto Layer**: Place the recipe in your Yocto layer under `recipes-ir/ir/`.
2. **Build the Image**: Include the `ir_1.0.bb` recipe in your image build.
3. **Ensure LIRC Is Installed**: The recipe ensures that the `lirc` package is installed and configured in the final image.
4. **Autoload Kernel Modules**: Kernel modules for LIRC will be automatically loaded on boot, ensuring that the system is ready for infrared remote control use.

---

### **Custom Configuration**
The recipe includes provisions for custom configuration paths and options, such as `LIRC_CONF_PATH` and `ENABLE_IR`. These variables are currently unused but can be extended for further customization as needed.

### **Important Notes**
- **Device Tree Overlay**: The `gpio-ir.dtbo` overlay configures the GPIO pins for infrared support. Ensure the correct GPIO pin is defined in this overlay.
- **Kernel Module Autoload**: The modules `lirc_dev` and `lirc_rpi` are required for LIRC to function and are automatically loaded by the recipe.
- **Boot Configuration**: The `config.txt` file ensures that the system boots with the necessary infrared hardware support.

---

### **File Structure**
```
recipes-ir/
├── ir
│   ├── files
│   │   ├── config.txt
│   │   ├── gpio-ir.dtbo
│   │   └── lircd.conf
│   └── ir_1.0.bb
└── lirc
    └── lirc_%.bbappend
```


---

### **LIRC Commands and Utilities**

Once LIRC is installed and configured on your system, you can use the following commands to manage the `lircd` service and test infrared signals.

#### 1. **Starting the `lircd` Service**

After building and installing the image with LIRC support, start the `lircd` (LIRC daemon) to begin receiving infrared signals:

```bash
# Start the lircd service
sudo systemctl start lircd

# Check the status of lircd to ensure it's running
sudo systemctl status lircd
```

To ensure `lircd` starts automatically on boot, enable the service:

```bash
# Enable lircd to start on boot
sudo systemctl enable lircd
```

#### 2. **Testing Infrared Reception with `mode2`**

You can use the `mode2` utility to test if the system is receiving infrared signals from the remote control:

```bash
# Run mode2 to check for incoming infrared signals
sudo mode2 -d /dev/lirc0
```

If everything is configured correctly, pressing buttons on the remote should show pulse and space values in the terminal, indicating signal reception.

#### 3. **Sending Infrared Signals with `irsend`**

The `irsend` utility can be used to send commands via infrared, which is useful for controlling other devices:

```bash
# Send a command using irsend
sudo irsend SEND_ONCE <remote_name> <button_name>
```

Example:

```bash
sudo irsend SEND_ONCE my_remote KEY_VOLUMEUP
```

Ensure that the remote name and button are defined in your `lircd.conf` configuration file.

#### 4. **Recording Remote Control Signals with `irrecord`**

You can use the `irrecord` utility to capture and generate a configuration file (`lircd.conf`) for your remote control:

```bash
# Start recording the remote control signals
sudo irrecord -d /dev/lirc0 ~/lircd.conf
```

Follow the on-screen instructions to press the buttons on your remote, and `irrecord` will generate a custom `lircd.conf` file.

#### 5. **Viewing Remote Control Signals with `irw`**

The `irw` utility allows you to monitor and log which buttons are being pressed on the remote:

```bash
# Run irw to check the output of the remote control
irw
```

This utility will output the button names as defined in your `lircd.conf` file when you press buttons on the remote.

