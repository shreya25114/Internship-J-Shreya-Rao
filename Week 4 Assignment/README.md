# Student Management System (Java 8 + JDBC)

## Requirements
- Java 8+, Maven, MySQL.

## Setup
1. Clone repo.
2. Create DB and table:
   - Run SQL in README (or `schema.sql`).
3. Configure DB credentials in `src/main/resources/application.properties`.
4. Build:
   mvn clean package
5. Run:
   java -jar target/student-management-1.0.0.jar
6. Or run from IDE: run `com.example.sms.Main`.

## Tests
mvn test

## Export
CSV export path configured in `application.properties` (`app.export.path`).

## Architecture
- `model` — DTOs
- `dao` — raw JDBC access (DAO pattern)
- `service` — business logic, validation
- `util` — config, DB connection, CSV export
- `validation` — validators
- `exception` — custom exceptions

## Notes
- Uses PreparedStatements and try-with-resources.
- Logging via SLF4J + Logback.
- To use connection pooling, swap `DBConnectionManager` to HikariCP.
