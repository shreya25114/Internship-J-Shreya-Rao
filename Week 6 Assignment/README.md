🚀 Employee Management System – Spring Security

A Spring Boot REST API demonstrating Spring Security fundamentals, including:

User authentication

Role-based authorization

Password encoding (BCrypt)

Secured endpoints

JWT-based authentication (bonus)

Exception handling

Clean API structure for Postman testing

This project implements a basic Employee Management System with ADMIN and USER roles.

📌 Features
🔐 1. Authentication

Supports login using Spring Security.

Users stored in H2/MySQL (or in-memory depending on your implementation).

Passwords encoded with BCryptPasswordEncoder.

🛡️ 2. Role-Based Authorization
Role	Capabilities
ADMIN	Full CRUD on employees
USER	Can only view employee details
🔒 3. Secured Endpoints
Endpoint	Method	Access
/api/employees/**	ALL	ADMIN only
/api/profile	GET	USER + ADMIN
📝 4. JWT (Bonus)

If your uploaded ZIP contains JWT:

/api/auth/login → returns JWT token

All secured endpoints require:

Authorization: Bearer <token>

⚠️ 5. Exception Handling

Graceful handling of:

401 Unauthorized

403 Forbidden

Custom JSON error responses

📂 Project Structure (Typical)
src/main/java/com/example/security/
│
├── config/
│   ├── SecurityConfig.java
│   ├── JwtFilter.java
│   └── JwtUtils.java
│
├── controller/
│   ├── AuthController.java
│   └── EmployeeController.java
│
├── entity/
│   ├── User.java
│   └── Employee.java
│
├── repository/
│   ├── UserRepository.java
│   └── EmployeeRepository.java
│
├── service/
│   ├── UserService.java
│   └── EmployeeService.java

🛠️ Tech Stack

Java 17+

Spring Boot 3.x

Spring Security

Spring Data JPA

JWT (jjwt or auth0)

H2/MySQL

Maven

▶️ Running the Project
1. Clone the repository
git clone https://github.com/<your-username>/<repo-name>.git
cd <repo-name>

2. Update database credentials (if using MySQL)

Edit application.properties.

3. Build & run
mvn spring-boot:run

4. Open H2 Console (if enabled)
http://localhost:8080/h2-console

👤 Default User Credentials
ADMIN
username: admin
password: admin123
role: ROLE_ADMIN

USER
username: user
password: user123
role: ROLE_USER


(Adjust according to your actual project’s data initialization.)

🔗 API Endpoints
🔐 Authentication
Method	Endpoint	Description
POST	/api/auth/login	Get JWT Token

Sample Request

{
  "username": "admin",
  "password": "admin123"
}

👥 Employee APIs (ADMIN only)
Method	Endpoint	Description
GET	/api/employees	List all employees
GET	/api/employees/{id}	Get employee by ID
POST	/api/employees	Create a new employee
PUT	/api/employees/{id}	Update employee
DELETE	/api/employees/{id}	Delete employee
🧑‍💼 Profile (USER + ADMIN)
Method	Endpoint	Description
GET	/api/profile	Get details of logged-in user
🧪 Postman Guide
Step 1: Login

Send POST request to:

/api/auth/login


Copy the token from the response.

Step 2: Add header for secured calls
Authorization: Bearer <token>
