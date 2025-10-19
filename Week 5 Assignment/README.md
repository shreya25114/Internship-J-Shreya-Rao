# Student Management System

## Overview
Spring Boot application demonstrating CRUD operations for a Student entity with validation, DTOs, global exception handling, Swagger docs and unit tests.

## Setup
1. Install Java 17+ and Maven.
2. Start MySQL and create database `student_db`.
3. Update `src/main/resources/application.properties` with DB credentials.
4. Build and run:
   ```bash
   mvn clean package
   mvn spring-boot:run
   ```
5. Swagger UI: `http://localhost:8080/swagger-ui/index.html`

## Endpoints
- POST /api/students -> Create
- GET /api/students -> List all
- GET /api/students/{id} -> Get by id
- PUT /api/students/{id} -> Update
- DELETE /api/students/{id} -> Delete

## Tests
Run:
```bash
mvn test
```

## Notes
- Uses DTOs, global exception handler, validation and Lombok.
- For integration tests, configure Testcontainers with a MySQL container.

