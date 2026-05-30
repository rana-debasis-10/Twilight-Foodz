# Twilight #

## Overview
Twilight is a backend project using Spring Boot. I have picked up the business model of a Food Delivery app.

## Tech Stack

- Java 21
- Spring Boot
- Spring Security (JWT Authentication)
- Razorpay
- MinIO
- MySQL
- Redis
- Docker

---

## Features
- JWT-based authentication & authorization OTP Based Login
- Role-based access control (Admin, Customer, Merchant, Driver, Restaurant Manager).
- Restaurant and Outlet Management.
- Item management. 
- Order creation & tracking.
- Online Payments and Webhook management.
- Secure API endpoints.
- Global exception handling.
- Logging system with file storage.
- Redis-based caching for fast response.
- Kafka Based Event Handling 
- MapStruct Mapping 
- S3 Compatible MinIO Storage
  
## Architecture 
Application follows a Layered Architecture: 

Controller → Service → Repository → Database

Some Processes are asynchronously handled and maintained

- Controllers handle HTTP requests and responses.
- Services contain business logic.
- Repositories handle database interaction using JPA.
- Security is implemented using JWT and Spring Security.
- Global exception handling is managed using @RestControllerAdvice.
- Redis is used for caching and session management.

## Installation and Setup
### Clone and open the Repository
``` bash

git clone https://github.com/rana-debasis-10/Twilight.org.git

cd Twilight.org

```
### Configure
Create src/main/resources/application.yaml file 
``` bash

mkdir -p src/main/resources/

cd src/main/resources/

nano application.yaml

```
Configure application.yaml
``` bash
#Spring Configuration ------------------------------------------#
spring:
  application:
    name: Twilight

  #Database connection -------------------------------------------#
  datasource:
    url: jdbc:mysql://localhost:3306/${Your_database_name}?useSSL=false&allowPublicKeyRetrieval=true
    username: ${YOUR_USER_NAME}
    password: ${YOUR_PASSWORD}
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    show-sql: false
    hibernate:
      ddl-auto: update
    database-platform: org.hibernate.dialect.MySQLDialect


  #Redis Configuration -------------------------------------------#
  redis:
    host: localhost
    port: 6379


#JWT Security ----------------------------------------------------#
jwt:
  secret:
    key: your-secret-key-here

#Razorpay Config ------------------------------------------------#
razorpay:
  key:
    id: ${RAZORPAY_PUBLIC_KEY}
    secret: ${RAZORPAY_PRIVATE_KEY}
  webhook:
    secret: ${YOUR_WEBHOOK_SECRET}

#Message Service Configuration ----------------------------------#
message:
  key: ${YOUR_SMS_SERVER_KEY}
  endpoint: ${YOUR_SMS_SERVER_ENDPOINT}


#Apache Kafka Configuration --------------------------------------#
kafka:
  bootstrap-servers: localhost:9092

  producer:
    key-serializer: org.apache.kafka.common.serialization.StringSerializer
    value-serializer: org.apache.kafka.support.serializer.JsonSerializer

  consumer:
    auto-offset-reset: earliest
    key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
    value-deserializer: org.apache.kafka.support.serializer.JsonDeserializer

#Storage Configuration -------------------------------------------#

storage:
  client:
    region: us-east-1
    endpoint: "http://localhost:9000"
    bucket: twilight_storage
  access-key: ${MINIO_USERNAME}
  secret-key: ${MINIO-PASSWORD}

#Logging Configuration --------------------------------------------#
logging:
  level:
    org.springframework.web: WARN


#Server Configuration ---------------------------------------------#
server:
  port: 8080

  error:
    include-message: always
```

## Export the Environment variables before building the project

### Run the project
``` bash

./gradlew bootRun 

```


## Note 
Project is under the process of development. I am working alone and still an undergraduate student. Do not expect a lot. 







