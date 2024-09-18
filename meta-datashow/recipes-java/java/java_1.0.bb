SUMMARY = "Java Development Kit (JDK 8) and Java Runtime Environment (JRE)"
DESCRIPTION = "This recipe provides the OpenJDK 8, its JRE, and the Datashow application."
LICENSE = "closed"

# Source JAR file
SRC_URI = "file://datashow.jar"

# Where to install the JAR file (in this case, /usr/bin)
DEST_DIR = "/usr/bin"

PREFERRED_PROVIDER_virtual/java-initial-native = "cacao-initial-native"
PREFERRED_PROVIDER_virtual/java-native = "cacao-native"
PREFERRED_PROVIDER_virtual/javac-native = "ecj-bootstrap-native"

# Add Java runtime as a dependency
RDEPENDS_${PN} = "openjdk-8-jre"

# Ensure both the JAR file and the wrapper script are included in the package
FILES_${PN} = "${DEST_DIR}/datashow.jar ${DEST_DIR}/datashow"

do_install() {
    # Install the JAR file in /usr/bin
    install -d ${D}${DEST_DIR}
    install -m 0755 ${WORKDIR}/datashow.jar ${D}${DEST_DIR}/datashow.jar

    # Create a wrapper script to run the JAR file
    echo '#!/bin/sh' > ${D}${DEST_DIR}/datashow
    echo 'exec java -jar /usr/bin/datashow.jar "$@"' >> ${D}${DEST_DIR}/datashow
    chmod 0755 ${D}${DEST_DIR}/datashow
}
