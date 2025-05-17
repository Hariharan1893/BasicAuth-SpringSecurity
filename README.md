# 🔐 Spring Boot Basic Authentication Example

This API is a complete demonstration of how to implement **Basic Authentication** in a Spring Boot application using **Spring Security**. It includes:

- Secure routes using HTTP Basic Auth
- User registration with role assignment
- Password encryption using BCrypt
- Custom `UserDetailsService`
- Persistent storage using JPA and MySQL

---

## 📁 Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [API Endpoints](#api-endpoints)
- [Authentication Flow](#authentication-flow)
- [Setup Instructions](#setup-instructions)
- [Sample `application.yml`](#sample-applicationyml)
- [Security Configuration Explained](#security-configuration-explained)
- [Testing with curl or Postman](#testing)
- [License](#license)

---

## ✅ Features

- User registration (`/signup`)
- Role-based access
- Spring Security's HTTP Basic Authentication
- Custom `UserDetailsService` for user lookup
- Passwords stored securely using BCrypt
- MySQL database integration

---

## 🧰 Tech Stack

- **Java 21**
- **Spring Boot 3.x**
- **Spring Security 6**
- **Spring Data JPA**
- **MySQL**
- **Lombok**
- **Maven**

---

## 🏗️ Project Structure

```

src/
├── controller/
│   ├── HomeController.java
│   └── SignUpController.java
├── model/
│   └── User.java
├── repository/
│   └── UserRepo.java
├── service/
│   ├── CustomUserDetailService.java
│   └── SignUpService.java
├── config/
│   └── SecurityConfig.java
└── SpringAuthenticationApplication.java

```

---

## 📡 API Endpoints

### `POST /signup`

Registers a new user.

#### Request Body

```json
{
  "username": "admin",
  "password": "admin123",
  "role": "ADMIN"
}
```

#### Response

```json
{
  "id": 1,
  "username": "admin",
  "role": "ADMIN"
}
```

---

### `GET /`

- Public access
- Returns: `"Welcome to the Home page...."`

---

### `GET /afterauth`

- Requires Basic Auth
- Returns: `"Authenticated"`

---

## 🔄 Authentication Flow

1. A new user signs up via the `/signup` endpoint.
2. Password is encrypted using `BCryptPasswordEncoder`.
3. The user is stored in the database with their role.
4. To access any secured endpoint (e.g., `/afterauth`), the client sends credentials using **Basic Auth**.
5. Spring Security verifies the user using the custom `UserDetailsService`.
6. If valid, access is granted.

---

## 🧪 Testing

### Register a User

```bash
curl -X POST http://localhost:9090/signup \
  -H "Content-Type: application/json" \
  -d '{"username": "john", "password": "secret123", "role": "USER"}'
```

### Access Public Endpoint

```bash
curl http://localhost:9090/
```

### Access Secured Endpoint

```bash
curl -u john:secret123 http://localhost:9090/afterauth
```

---

## ⚙️ Security Configuration Explained

In `SecurityConfig.java`:

```java
http
  .csrf(csrf -> csrf.disable())
  .authorizeHttpRequests(auth ->
      auth.requestMatchers("/", "/signup").permitAll()
          .anyRequest().authenticated())
  .httpBasic();
```

- `/` and `/signup` are **public**.
- All other routes require HTTP Basic Authentication.
- Passwords are checked using the custom `UserDetailsService`.

---

## 🧾 Sample `application.yml`

Create a file at `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/yourdb
    username: root
    password: yourpassword
    driver-class-name: com.mysql.cj.jdbc.Driver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL8Dialect

server:
  port: 9090
```

🔐 **Note:** Don’t commit this file — it's ignored via `.gitignore`.
Instead, commit a `application-example.yml` with placeholders.

---

## 🚀 Setup Instructions

1. Clone the repository:

```bash
git clone https://github.com/Hariharan1893/BasicAuth-SpringSecurity.git
cd BasicAuth-SpringSecurity
```

2. Add your DB config in `src/main/resources/application.yml`

3. Run the application:

```bash
mvn spring-boot:run
```

---

## 🧼 .gitignore Recommendations

Ensure `application.yml` is ignored:

```gitignore
src/main/resources/application.yml
```

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).

---

## 👨‍💻 Author

Crafted with ❤️ using Spring Boot.
