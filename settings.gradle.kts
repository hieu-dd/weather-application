plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "weather"
include("common")
include("service")
include("ip2location")
