do_install:append() {
    # Remove the lircd.conf file after installation
    rm -rf ${D}${sysconfdir}/lirc/lircd.conf
}
