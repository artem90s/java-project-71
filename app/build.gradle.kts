plugins {
    id("java")
    id ("com.github.ben-manes.versions") version "0.53.0"
    application
    checkstyle
    id("org.sonarqube") version "7.2.3.7755"
    id("jacoco")
}

sonar {
    properties {
        property("sonar.projectKey", "artem90s_java-project-71")
        property("sonar.organization", "artem90s")
        property ("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/test/jacocoTestReport.xml")
    }
}

jacoco {
    toolVersion = "0.8.11"
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
    implementation ("info.picocli:picocli:4.7.7")
    implementation ("tools.jackson.core:jackson-databind:3.0.0")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}