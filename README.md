# REST API with Spring Boot and Angular

![Build](https://github.com/wmcruz/crud-angular/actions/workflows/node.js.yml/badge.svg?branch=master)

CRUD Angular + Spring course teacher Loiane. [Repo](https://github.com/loiane/crud-angular-spring/)

This is a demonstration project on how to create a CRUD application in Angular.

## 💻 Tecnologies
- Java 17
- Spring Boot 3 (Spring 6)
- Maven
- JPA + Hibernate
- MySQL
- JUnit 5 + Mockito (back-end tests)

## ⌨️ Editor / IDE
- [IntelliJ](https://www.jetbrains.com/idea/download)
- [VsCode](https://code.visualstudio.com/download)

## Some functionalities available in the API
- ✅ Java model class with validation
- ✅ JPA repository
- ✅ JPA Pagination
- ✅ MySQL database (you can use any database of your preference)
- ✅ Controller, Service, and Repository layers
- ✅ Has-Many relationships (Course-Lessons)
- ✅ Java 17 Records as DTO (Data Transfer Object)
- ✅ Hibernate / Jakarta Validation
- ✅ Unit tests for all layers (repository, service, controller)
- ✅ Test coverage for tests
- ✅ Spring Docs - Swagger (https://springdoc.org/v2/)

### Not implemented (maybe in a future version)

- Security (Authorization and Authentication)
- Caching
- Data Compression
- Throttling e Rate-limiting
- Profiling the app
- Test Containers
- Docker Build
## ❗️Executing the code locally

### Executing the back-end
You need to have Java, Maven and MySql installed and configured locally.

In the case of MySQL, you can use a database of your choice. For the MySQL database, we already have a Docker container created to facilitate execution.

1. Start MySql with Docker:
```
docker compose up -d
```

2. Execute the project:
Open the `crud-spring` project in your favorite IDE as a Maven project and execute it as Spring Boot application.

### Executing the front-end
You need to have Node.js / NPM installed locally.

Open the [crud-angular](https://github.com/wmcruz/crud-angular) project in your favorite IDE and follow the instructions.