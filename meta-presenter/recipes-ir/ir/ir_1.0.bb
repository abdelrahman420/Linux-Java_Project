# The summary provides a brief description of what the recipe does
SUMMARY = "Linux Infrared Remote Control (LIRC) support"

# The description gives a more detailed explanation of the recipe's purpose
DESCRIPTION = "This recipe installs and configures LIRC, which allows you to use infrared remote control devices with Linux."

# License for the recipe, "CLOSED" means it's proprietary or custom
LICENSE = "CLOSED"

# This appends the 'lirc' package to IMAGE_INSTALL, ensuring it is included in the image
IMAGE_INSTALL:append = " lirc"

# This adds 'lirc' to MACHINE_FEATURES to indicate that the machine supports LIRC
MACHINE_FEATURES:append = " lirc"

# Adds kernel modules for LIRC support, autoloads them on boot
KERNEL_MODULE_AUTOLOAD += "lirc_dev lirc_rpi"

# Custom variable for the LIRC configuration file path (currently not used in the recipe)
LIRC_CONF_PATH = "/etc/lirc/lirc.conf"

# Path for the overlay file
OVERLAY_PATH = "/boot/overlays"


# Custom variable to enable infrared (currently not used in the recipe)
ENABLE_IR = "1"

# The S variable defines the source directory. WORKDIR is a common location
S = "${WORKDIR}"

# Specifies the files that are included in the final package
FILES_${PN} = "${sysconfdir}/lirc/lircd.conf ${OVERLAY_PATH}/gpio-ir.dtbo"

# SRC_URI defines the location of source files.
# Include both lircd.conf and gpio-ir.dtbo files
SRC_URI = "file://lircd.conf \
           file://gpio-ir.dtbo \
           file://boot/config.txt"


# The do_install function defines what happens during the installation phase
do_install() {
    # Creates the /etc/lirc directory in the root filesystem of the target image
    install -d ${D}${sysconfdir}/lirc

    # Copies the custom lircd.conf file to the /etc/lirc directory
    install -m 0644 lircd.conf ${D}${sysconfdir}/lirc

    # Install the overlay file to /boot/overlays
    install -d ${D}${OVERLAY_PATH}
    install -m 0644 gpio-ir.dtbo ${D}${OVERLAY_PATH}/gpio-ir.dtbo

    # Install the provided config.txt to /boot
    install -d ${D}${BOOT_PATH}
    install -m 0644 config.txt ${D}${BOOT_PATH}/config.txt
}
