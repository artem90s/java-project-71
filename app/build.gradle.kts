plugins {
    id("java")
    id ("com.github.ben-manes.versions") version "0.53.0"
    application
}

application {
    mainClass.set("hexlet.code.App")
}

group = "hexlet.hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}