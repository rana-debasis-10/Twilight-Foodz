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
- JWT-based authentication & authorization and email based login.
- Role-based access control (Admin, Cusotomer, Merchant, Driver, Restaurant Manager).
- Restaurant and Outlet Management.
- Item management. 
- Order creation & tracking.
- Online Payments and Webhook management.
- Secure API endpoints.
- Global exception handling.
- Logging system with file storage.
- Redis-based caching for fast response.
  
## Architecture 
Application follows a Layered Architecture: 

Controller → Service → Repository → Database

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

mkdir src/main/resources

nano application.yaml

```
Configure application.yaml
``` bash
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/Twilight?useSSL=false&allowPublicKeyRetrieval=TRUE
    username: user_name
    password: user_password

  application:
    name: Twilight
  jpa:
    show-sql: true
    hibernate:
      ddl-auto: update
    database-platform:
      org.hibernate.dialect : MySQLDialect
    
logging:
  level:
    org.springframework.web : INFO
    
server:
  error:
    include-message: always
  port: 8080

management:
  endpoints:
    web:
      exposure:
        include: health,info

 razorpay:
  key:
    id: rzp_test_xxxxxxxxxxxxxx
    secret: xxxxxxxxxxxxxxxxxxxxxxxx
  webhook:
    secret: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

storage:
  bucket: TwilightImageStorage

jwt:
  secret:
    key: XXXXXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
aws:
  region: your_aws_region
  endpoint: your_aws_endpoint
  access-key: your_aws_access_key
  secret-key: your_aws_secret_key
```
### Run the project 
``` bash

./gradlew bootRun --stacktrace

```
this builds and runs the project on localhost at 8080 port 

## Note 
Project is under the process of development. I am working alone and still a undergraduate student. Do not expect a lot. 







