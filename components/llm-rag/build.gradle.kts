import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "3.3.2"
}

dependencies {
    implementation("org.springframework:spring-context")
    implementation("org.springframework.ai:spring-ai-ollama-spring-boot-starter")
    implementation("org.springframework.ai:spring-ai-redis-store-spring-boot-starter")
    implementation("org.springframework.ai:spring-ai-pdf-document-reader")
}

tasks.withType<BootJar> {
    enabled = false
}

