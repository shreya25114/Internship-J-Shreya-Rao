🛡️ Employee Management System — Spring Security Assignment

A Spring Boot application demonstrating authentication, authorization, password encoding, role-based access control, and optional JWT security using Spring Security.

📌 Features Implemented ✅ 1. User Authentication

Spring Security login (default or custom login API)

Users stored in H2 database (can switch to MySQL)

Passwords encoded using BCryptPasswordEncoder

✅ 2. Role-Based Authorization

Defined roles:

ADMIN

Full CRUD on employees (/api/employees/**)

USER

Can only view their profile (/api/profile)

✅ 3. Secured REST Endpoints Endpoint Method Access /api/employees GET ADMIN /api/employees/{id} GET ADMIN /api/employees POST ADMIN /api/employees/{id} PUT ADMIN /api/employees/{id} DELETE ADMIN /api/profile GET USER / ADMIN ⚡ 4. JWT Implementation (Bonus)

Stateless authentication

/api/auth/login issues JWT token

Token validation filter added in security chain

Secured every endpoint except login and signup

🛑 5. Exception Handling

Custom error responses for:

401 Unauthorized

403 Forbidden

Invalid/Expired JWT tokens

📂 Project Structure src/main/java/com/example/ems │ ├── config │ ├── SecurityConfig.java │ ├── JwtAuthFilter.java │ └── JwtUtils.java │ ├── controller │ ├── AuthController.java │ ├── EmployeeController.java │ └── ProfileController.java │ ├── entity │ ├── User.java │ └── Employee.java │ ├── repository │ ├── UserRepository.java │ └── EmployeeRepository.java │ ├── service │ ├── UserService.java │ └── EmployeeService.java │ └── EmsApplication.java

🗄️ Database

You can use:

H2 (default)

MySQL (optional)

H2 Console http://localhost:8080/h2-console

Default JDBC URL:

jdbc:h2:mem:testdb

👥 Default Users Role Email Password ADMIN admin@ems.com admin123 USER user@ems.com user123

(Passwords stored encoded with BCrypt)

🚀 How to Run

Clone the repository git clone https://github.com/your-username/spring-security-ems.git cd spring-security-ems

Build & Run mvn spring-boot:run

Access API

Server runs at:

http://localhost:8080

🧪 API Testing (Postman / cURL) 🔑 Login to get JWT Token POST /api/auth/login { "email": "admin@ems.com", "password": "admin123" }

🧵 Use Token

Add header:

Authorization: Bearer

🧑‍💼 ADMIN – Get All Employees GET /api/employees Authorization: Bearer

👤 Profile – USER & ADMIN GET /api/profile Authorization: Bearer

📬 Postman Collection

A collection containing: ✔ Login ✔ Employee API tests ✔ Profile endpoint

(Attach JSON file or add link here)

📘 Technologies Used

Java 17+

Spring Boot 3.x

Spring Security 6+

JWT

BCrypt

H2 / MySQL

Maven

➕ Bonus Features Implemented (If applicable)

Method-level security using @PreAuthorize

Custom authentication entry point

Global exception handler

DTO + Mapper pattern

📝 Future Improvements

Refresh tokens

Logout with blacklist

User registration with email verification

UI using Angular/React

📄 License

MIT License (optional)
