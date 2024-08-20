import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "3.3.2"
}

dependencies {
    implementation("org.springframework:spring-context")
}

tasks.withType<BootJar> {
    enabled = false
}
