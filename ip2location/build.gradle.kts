plugins {
    kotlin("jvm")
}

group = "org.bakarot"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.google.code.gson:gson:2.8.8") // Gson
    implementation("org.apache.commons:commons-lang3:3.12.0") // Apache Commons Lang
    implementation("org.apache.commons:commons-csv:1.8") // Apache Commons CSV
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(18)
}