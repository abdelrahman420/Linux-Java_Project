SUMMARY = "Yocto Image with Sato Desktop for Running Java Applications for a remote presenter"
DESCRIPTION = "Image with Sato to run Java Application"
HOMEPAGE = "https://www.yoctoproject.org/"

LICENSE = "MIT"
PACKAGE_CLASSES ?= "package_rpm opkg"

# Image Features
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
