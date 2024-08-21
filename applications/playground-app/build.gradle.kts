plugins {
	id("org.springframework.boot") version "3.3.2"
}

group = "com.ahmedmq"



dependencies {
	implementation(project(":components:llm-rag"))
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	runtimeOnly("org.webjars:webjars-locator-core")
	runtimeOnly("org.webjars.npm:bootstrap:5.3.3")
	runtimeOnly("org.webjars.npm:popperjs__core:2.11.7")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}






