plugins {
	java
	id("org.springframework.boot") version "4.0.2"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com"
version = "0.0.1-SNAPSHOT"
description = "Production grade Spring Boot project"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation( "com.razorpay:razorpay-java:1.4.8")
    implementation(platform("software.amazon.awssdk:bom:2.25.61"))
    implementation("software.amazon.awssdk:s3")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation ("org.springframework.kafka:spring-kafka")
    compileOnly("org.projectlombok:lombok")
	runtimeOnly("com.mysql:mysql-connector-j");
    implementation ("io.jsonwebtoken:jjwt-api:0.13.0")
    runtimeOnly ("io.jsonwebtoken:jjwt-impl:0.13.0")
    runtimeOnly ("io.jsonwebtoken:jjwt-jackson:0.13.0")
	annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
	testImplementation("org.springframework.boot:spring-boot-starter-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
