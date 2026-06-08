plugins {
	java
	id("org.springframework.boot") version "4.0.2"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com"
version = "0.0.1"
description = "Mini Zomato with contracted relational model"

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
	//Spring JPA Dependency
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	//Spring Security Dependency
	implementation("org.springframework.boot:spring-boot-starter-security")

	//Spring Starter Web Dependency
	implementation("org.springframework.boot:spring-boot-starter-web")

	//Spring Validations Dependency
	implementation("org.springframework.boot:spring-boot-starter-validation")

	//Razorpay Dependency
    implementation( "com.razorpay:razorpay-java:1.4.8")

	//AWS Development Platform
    implementation(platform("software.amazon.awssdk:bom:2.25.61"))

	//MINIO Dependency for AWS
    implementation("software.amazon.awssdk:s3")

	//Redis Dependency
	implementation ("org.springframework.boot:spring-boot-starter-data-redis")

	//Cache Dependency
	implementation ("org.springframework.boot:spring-boot-starter-cache")

	//Apache Kafka Dependency
	implementation ("org.springframework.kafka:spring-kafka")

	//MapStruct Dependency
	implementation("org.mapstruct:mapstruct:1.5.5.Final")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")

	//Lombok Dependency
	compileOnly("org.projectlombok:lombok")

	//Lombok Annotation Processor Dependency
	annotationProcessor("org.projectlombok:lombok")

	//Mapstruct-Lombok Binding
	annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")

	//MySQL Connector Dependency
	runtimeOnly("com.mysql:mysql-connector-j");

	runtimeOnly("org.postgresql:postgresql:42.7.4")

	//JWT API Dependencies
    implementation ("io.jsonwebtoken:jjwt-api:0.13.0")

	//JWT API Implementations
    runtimeOnly ("io.jsonwebtoken:jjwt-impl:0.13.0")

	//JWT to JSON converter
    runtimeOnly ("io.jsonwebtoken:jjwt-jackson:0.13.0")



}

tasks.withType<Test> {
	useJUnitPlatform()
}
