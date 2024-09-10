SUMMARY = "Java Development kit JDK 8 and Java Runtime Environment"
DESCRIPTION = "This recipe provides the OpenJDK 8 and its JRE"

PREFERRED_PROVIDER_virtual/java-initial-native = "cacao-initial-native"
PREFERRED_PROVIDER_virtual/java-native = "cacao-native"
PREFERRED_PROVIDER_virtual/javac-native = "ecj-bootstrap-native"

IMAGE_INSTALL:append = " openjdk-8"
