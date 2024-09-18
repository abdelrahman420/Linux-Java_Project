#  Creating a Yocto Image with Sato Desktop for Running Java Applications

## Introduction

This guide provides instructions for building a Yocto image that includes the **Sato desktop** environment, as well as support for **Java applications** and additional features like **SSH** and **OpenJDK**. The image is customized for use in a remote presenter setup.

---

## 1. Image Summary

- **SUMMARY**: Yocto Image with Sato Desktop for Running Java Applications for a remote presenter
- **DESCRIPTION**: This image includes the Sato desktop environment and support for running Java applications.
- **HOMEPAGE**: [Yocto Project](https://www.yoctoproject.org/)

---

## 2. License

This image recipe is licensed under the **MIT** License:

```bash
LICENSE = "MIT"
```

---

## 3. Package Classes

Specify the package formats that will be used for creating the image:

```bash
PACKAGE_CLASSES ?= "package_rpm opkg"
```

This ensures that both **RPM** and **OPKG** package managers are supported.

---

## 4. Image Features

### Enabling Features
The **IMAGE_FEATURES** variable allows you to enable specific features in the image. For example, to include features like **splash screen**, **package management**, and **SSH server**, you can add:

```bash
IMAGE_FEATURES += "splash package-management x11-base x11-sato ssh-server-dropbear hwcodecs"
```

These features include graphical support for **X11** and **Sato**, along with the **Dropbear SSH server** and hardware codecs for multimedia processing.

---

## 5. Image Customization with `IMAGE_INSTALL`

### Purpose of `IMAGE_INSTALL`
`IMAGE_INSTALL` is a crucial variable that defines the packages to be installed in the final image. You can customize your image by adding or removing packages using this variable.

#### Example: Defining Packages in `datashow-image.bb`

Here’s an example of using `IMAGE_INSTALL` to add base packages and customize the software included in the image:

```bash
IMAGE_INSTALL += "base-files busybox"
IMAGE_INSTALL_append = " openssh-server"
```

To further customize the image, append additional packages using `IMAGE_INSTALL_append`. For this project, we need to include packages for **Java**, **remote control support**, and other tools:

```bash
IMAGE_INSTALL:append = " java ir lirc evince openjdk-8 opkg python3 python3-pkg-resources vim"
```

This adds support for **Java**, infrared remote control (via **lirc**), and additional tools like the **Evince** PDF viewer and **OpenJDK-8** for Java.

---

## 6. Managing Layers with `bblayers.conf`

Layer management is essential for ensuring that your Yocto build includes all required metadata and dependencies. Ensure that your **bblayers.conf** file includes the relevant layers, such as **meta-openembedded** and **meta-raspberrypi**, to avoid build conflicts.

---

## 7. Configuration Overriding with `local.conf`

You can override default settings in your **local.conf** file without changing the core layer files. This provides flexibility in your builds and allows for project-specific customizations.

---

## 8. Fine-Tuning Machine and Distro Configurations

You can further customize your image by defining **machine-specific** and **distribution-specific** configurations. For example, you can adjust memory settings for QEMU virtual machines depending on the machine architecture.

### Memory Configuration for QEMU

Here’s an example of how to configure memory settings for QEMU virtual machines based on whether the **OpenGL** feature is enabled:

```bash
QB_MEM = '${@bb.utils.contains("DISTRO_FEATURES", "opengl", "-m 512", "-m 256", d)}'
QB_MEM:qemuarmv5 = "-m 256"
QB_MEM:qemumips = "-m 256"
```

---

## 9. SDK Configuration

If you're developing software for this image and need to configure the SDK, you can use the **TOOLCHAIN_HOST_TASK** variable to include necessary native SDK tools:

```bash
TOOLCHAIN_HOST_TASK:append = " nativesdk-intltool nativesdk-glib-2.0"
TOOLCHAIN_HOST_TASK:remove:task-populate-sdk-ext = " nativesdk-intltool nativesdk-glib-2.0"
```

This ensures that your SDK includes **intltool** and **glib**, while removing them from certain tasks to streamline the SDK generation process.

---

## 10. Creating the Image Recipe

### Step 1: Create or Edit the Image Recipe

Navigate to your layer's **recipes-core/images** directory and create a new image recipe file, for example `datashow-image.bb`:

```bash
cd ~/yocto/meta-datashow/recipes-core/images
touch datashow-image.bb
```

### Step 2: Define the Image Recipe

Open the image recipe file (`datashow-image.bb`) and add the following content to define your custom image:

```bash
SUMMARY = "Yocto Image with Sato Desktop for Running Java Applications for a remote presenter"
DESCRIPTION = "Image with Sato to run Java Application"
LICENSE = "MIT"
HOMEPAGE = "https://www.yoctoproject.org/"

IMAGE_FEATURES += "splash package-management x11-base x11-sato ssh-server-dropbear hwcodecs"

IMAGE_INSTALL:append = " java ir lirc evince openjdk-8 opkg python3 python3-pkg-resources vim"

inherit core-image

# SDK Configuration
TOOLCHAIN_HOST_TASK:append = " nativesdk-intltool nativesdk-glib-2.0"
TOOLCHAIN_HOST_TASK:remove:task-populate-sdk-ext = " nativesdk-intltool nativesdk-glib-2.0"

# Memory Settings for QEMU
QB_MEM = '${@bb.utils.contains("DISTRO_FEATURES", "opengl", "-m 512", "-m 256", d)}'
QB_MEM:qemuarmv5 = "-m 256"
QB_MEM:qemumips = "-m 256"
```

---

## 11. Inheriting from `core-image`

Inherit the **core-image** class to ensure your image includes basic Yocto features and tools:

```bash
inherit core-image
```

---

## 12. Building Your Custom Image

Once your image recipe is ready, you can build it using **BitBake**:

```bash
bitbake datashow-image
```

This command will generate an image based on the defined packages and features in the `datashow-image.bb` recipe.

---

## Conclusion

This README provides a comprehensive guide to creating a Yocto image tailored for running Java applications with the Sato desktop environment. By following the steps and customization options outlined here, you can create a flexible, feature-rich image suited for remote presenter setups.

For additional information, refer to the [Yocto Project Documentation](https://www.yoctoproject.org/documentation/).

