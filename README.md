# BIT235 Assessment 2 - Part A: Wiki Administrator Login

**Author:** Pei Jingyi  **Student ID:** S1580654  **Unit:** BIT235 Object Oriented Programming (Java OOP)

A Spring Boot MVC web application that presents a login screen, authenticates the
submitted username/password against hard-coded values, and shows a welcome page on
success or an error page on failure. No database is used in Part A.

## Hard-coded credentials

| Field | Value |
|-------|-------|
| Username | `pei` (first name in lower case) |
| Password | `123` |

## Project structure (MVC / layered architecture)

```
wiki-login/
├── pom.xml                                   Maven build + dependencies
└── src/main/
    ├── java/com/melbourne/bit235/wikilogin/
    │   ├── WikiLoginApplication.java          Entry point (@SpringBootApplication)
    │   ├── controller/
    │   │   └── LoginController.java           View/Control: GET + POST mappings
    │   ├── service/
    │   │   └── AuthService.java               Business logic: credential check
    │   ├── repository/
    │   │   ├── AuthRepository.java            Data-access interface
    │   │   └── HardcodedAuthRepository.java   Hard-coded credentials (no DB)
    │   └── model/
    │       ├── LoginForm.java                 Form-backing bean (View <-> Controller)
    │       └── Account.java                   Stored account value object
    └── resources/
        ├── application.properties             Server/template configuration
        ├── templates/                         VIEW layer (Thymeleaf)
        │   ├── login.html
        │   ├── welcome.html
        │   └── error.html
        └── static/css/style.css               Shared page styling
```

## How to run

1. Make sure Java 21 (or 17+) and Maven are installed.
2. From the project root run: `mvn spring-boot:run`
3. Open <http://localhost:8080/login> in a browser.
4. Log in with `pei` / `123` for the welcome page; any other values show the error page.

To build the executable JAR: `mvn clean package`, then `java -jar target/wiki-login-0.0.1-SNAPSHOT.jar`.

## Request flow (for the demo)

1. Browser sends `GET /login` -> `LoginController.showLoginPage` adds an empty `LoginForm` to the Model and returns the `login` view.
2. User submits the form -> `POST /login` with `username` and `password` bound to `LoginForm` (`@ModelAttribute`, `@Valid`).
3. `LoginController.processLogin` calls `AuthService.authenticate(form)`.
4. `AuthService` asks `AuthRepository.findAdminAccount()` for the stored credentials and compares both fields.
5. Match -> Controller adds `username` to the Model and returns `welcome`; no match -> adds `errorMessage` and returns `error`.

## Testing

Run `mvn test` to execute the JUnit tests covering the Service logic and the GET/POST view flow.
