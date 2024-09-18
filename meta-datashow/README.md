
# Meta-Datashow Yocto Layer

## Overview

The `meta-datashow` layer is a Yocto Project layer designed to provide a custom distribution and image tailored for running Java applications and supporting Infrared Remote Control (LIRC). This layer includes recipes for configuring and building images with the necessary components, including a Java application, LIRC support, and custom configurations.

## Directory Structure

```
meta-datashow/
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
└── recipes-java
    └── java
        └── java_1.0.bb
```

## Contents

### `conf/`

- **`distro/datashow.conf`**: Configuration file for the Datashow distribution, defining key aspects such as the kernel version, C library, init system, and package manager.
- **`layer.conf`**: Configuration file for the meta-datashow layer, setting layer priority and defining paths to other layers.

### `COPYING.MIT`

- **MIT License**: Contains the licensing terms for the meta-datashow layer, using the MIT license.

### `README`

- **Layer README**: Provides detailed information about the layer, its components, and how to use it.

### `recipes-image/`

- **`image/datashow-image.bb`**: BitBake recipe for creating a custom image that includes the Datashow application and relevant configurations.

### `recipes-ir/`

- **`ir/`**: Contains recipes and configuration files for Infrared Remote Control (LIRC).
  - **`files/`**: Directory containing necessary configuration files for LIRC.
    - **`config.txt`**: Boot configuration file for initializing infrared hardware.
    - **`gpio-ir.dtbo`**: Device tree overlay for configuring GPIO pins for infrared reception.
    - **`lircd.conf`**: LIRC configuration file mapping remote control signals to events.
    - **`lirc_options.conf`**: Default options for the LIRC daemon.
  - **`ir_1.0.bb`**: Main BitBake recipe for installing and configuring LIRC.
  - **`lirc/lirc_%.bbappend`**: Appends configurations to the existing LIRC package to ensure proper support.

### `recipes-java/`

- **`java/java_1.0.bb`**: BitBake recipe for installing the Datashow Java application (`datashow.jar`) and ensuring the OpenJDK 8 runtime environment is available.

## How to Use

### Adding the Layer

1. **Clone the Layer:**
   ```bash
   git clone <repository-url> meta-datashow
   ```

2. **Add the Layer to Your Build:**
   Add the `meta-datashow` layer to your `bblayers.conf` file:
   ```bash
   BBLAYERS += "/path/to/meta-datashow"
   ```

### Customizing the Distribution

1. **Edit `datashow.conf`:**
   Customize the distribution settings, including kernel version, package manager, and init system.

2. **Configure Your Image:**
   Modify `datashow-image.bb` to include additional packages or features as needed.

### Building the Image

1. **Run BitBake:**
   Build your image with the custom configuration:
   ```bash
   bitbake datashow-image
   ```

### Using the Recipes

1. **Infrared Remote Control (LIRC):**
   - Ensure `ir_1.0.bb` and `lirc_%.bbappend` are included in your build.
   - The LIRC configuration files are located in `recipes-ir/ir/files/`.

2. **Java Application:**
   - The `java_1.0.bb` recipe installs `datashow.jar` to `/usr/bin` and creates a wrapper script.
   - The application can be run using the `datashow` command.

## Key Variables and Configuration

### `datashow-image.bb`

- **`IMAGE_INSTALL`**: Defines the packages and features included in the image, such as the Datashow application and LIRC support.

### `ir_1.0.bb`

- **`FILES_${PN}`**: Specifies the files included in the final package, such as LIRC configuration files.
- **`KERNEL_MODULE_AUTOLOAD`**: Ensures necessary kernel modules are loaded on boot.

### `java_1.0.bb`

- **`SRC_URI`**: Points to the `datashow.jar` file.
- **`DEST_DIR`**: Defines the installation directory for the JAR file and wrapper script.
- **`RDEPENDS_${PN}`**: Specifies dependencies like OpenJDK 8.

## Notes

- **Device Tree Overlay**: Ensure the `gpio-ir.dtbo` file is correctly configured for your hardware.
- **Java Runtime**: The `java_1.0.bb` recipe depends on OpenJDK 8. Make sure it is available in your build environment.
- **LIRC Configuration**: Modify `lircd.conf` and `lirc_options.conf` as needed for your specific remote control setup.

## License

The `meta-datashow` layer is licensed under the MIT license, as specified in the `COPYING.MIT` file.
