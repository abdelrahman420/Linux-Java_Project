
# Creating a Custom Yocto Distribution (datashow)

## Introduction

This README provides detailed instructions on creating a custom Yocto distribution called **datashow**. It explains the directory structure, key configuration files, and the customization of distribution features such as package manager, init system, C library, and more.

## Prerequisites

- Familiarity with Yocto Project and BitBake
- Installed Yocto environment (Poky setup)
- Basic understanding of Linux distributions and system configurations

---

## 1. Distribution Configuration Hierarchy

To create a new distribution, follow Yocto's standard directory hierarchy:

```
layer (meta)
└── conf
     └── distro
           └── datashow.conf
```

### Example: Poky Distribution Structure
For reference, the configuration for the **Poky** distribution is located at:

```
~/yocto/poky/meta-poky/conf/distro/poky.conf
```

### Steps to Create Your Own Distribution
To create a custom distribution, you will follow a similar hierarchy. For example, if your distribution is called **datashow**, the structure will look like:

```
~/yocto/meta-datashow/conf/distro/datashow.conf
```

You can include the **Poky** configuration to reuse its settings and simplify your setup:

```bash
include meta-poky/conf/distro/poky.conf
```

---

## 2. Key Variables to Customize in Your Distribution

The primary variables in your custom distribution configuration file (`datashow.conf`) include:

```bash
DISTRO = "datashow"
DISTRO_NAME = "datashow"
DISTRO_VERSION = "4.0.20"
DISTRO_CODENAME = "kirkstone"
MAINTAINER = "datashow@outlook.com"
```

These values define the name, version, and maintainer information of your distribution.

---

## 3. Differences Between Yocto Distributions

When creating a custom distribution, consider the following components that may vary between distributions:

### 3.1 Package Manager

Distributions use different package managers to handle packages:

- **IPK**: OpenEmbedded-based systems
- **RPM**: Distributions like Fedora
- **DEB**: Debian-based systems

Example path for RPM packages:
```
~/yocto/poky/build/tmp/deploy/rpm
```

### 3.2 Library

The C library can vary based on your system’s requirements:

- **glibc**: The standard GNU C Library
- **uclibc**: A smaller alternative for embedded systems
- **musl**: A lightweight, simple C library

### 3.3 Init Process

Different distributions use different init systems to manage system services:

- **BusyBox**: Lightweight init system
- **systemd**: Modern and feature-rich init system
- **SystemV**: Traditional Unix init system

### 3.4 Kernel Version

Distributions may also use different Linux kernel versions. You can customize this in the `machine.conf` file for your target hardware.

---

## 4. Creating a Custom Distribution Configuration

### Step 1: Navigate to the Distribution Directory

```bash
cd ~/yocto/meta-datashow/conf/distro
```

### Step 2: Create the Distribution Configuration File

```bash
code datashow.conf
```

### Step 3: Add the Custom Configuration

```bash
# Include the base poky distribution
include conf/distro/poky.conf

# Define your custom distribution name and version
DISTRO = "datashow"
DISTRO_NAME = "datashow"
DISTRO_VERSION = "4.0.20"
DISTRO_CODENAME = "kirkstone"

# Enable systemd as the init manager
DISTRO_FEATURES:append = " systemd"
VIRTUAL-RUNTIME_init_manager = "systemd"

# Optional: Define additional systemd-related runtime managers
DISTRO_FEATURES_BACKFILL_CONSIDERED:append = " sysvinit"
VIRTUAL-RUNTIME_initscripts = "systemd-compat-units"
VIRTUAL-RUNTIME_login_manager = "shadow-base"
VIRTUAL-RUNTIME_dev_manager = "systemd"
```

---

## 5. Enabling systemd

To enable **systemd** as the default init system for your distribution, append the required features in your `datashow.conf` file.

You can reference the **systemd** configuration from:

```bash
~/yocto/poky/meta/conf/distro/include/init-manager-systemd.inc
```

---

## 6. Example Custom Distribution Configuration

Here’s a complete example of a custom configuration in `datashow.conf`:

```bash
# Include base Poky distribution configuration
include meta-poky/conf/distro/poky.conf

# Distribution metadata
DISTRO = "datashow"
DISTRO_NAME = "datashow"
DISTRO_VERSION = "4.0.20"
DISTRO_CODENAME = "kirkstone"
CONF_VERSION = "2"

# Features and system components
DISTRO_FEATURES:append = " systemd x11 wayland opengl gtk3 gnome dbus"
VIRTUAL-RUNTIME_init_manager = "systemd"
VIRTUAL-RUNTIME_initscripts = "systemd-compat-units"
DISTRO_FEATURES_BACKFILL_CONSIDERED += "sysvinit"

# Package management and image configuration
IMAGE_INSTALL:append = " dropbear systemd systemd-serialgetty"
IMAGE_FSTYPES = "tar.xz ext3 rpi-sdimg"

# Additional configurations
INHERIT = "rm_work"
ENABLE_UART = "1"


# Kernel version preference
PREFERRED_VERSION_linux-yocto = "5.15%"
```


## Conclusion

This README provides a comprehensive guide to creating a custom Yocto distribution. By following the hierarchy and customization steps outlined above, you can develop a tailored distribution that fits your project’s requirements.

For further reference, see the [Yocto Project Documentation](https://www.yoctoproject.org/documentation/).

