# Smart School ERP Platform

Smart School ERP Platform is a Spring Boot application that provides REST APIs to manage student and staff records, attendance, examinations, and user authentication (JWT). It uses MySQL for data storage and includes OpenAPI/Swagger for API documentation — suitable as a backend for admin portals or mobile apps.

## Features

- REST API endpoints for students, staff, attendance, exams, and roles/permissions
- JWT-based authentication and role-driven access control
- MySQL persistence with Spring Data JPA
- OpenAPI (Swagger) UI for interactive API documentation

## Project structure (high level)

- src/main/java/com/springboot/schoolmanagement: application code (controllers, services, repositories, entities, DTOs, security)
- src/main/resources: configuration and static resources
- pom.xml: Maven build file with Spring Boot, JJWT, springdoc OpenAPI, and MySQL connector

## Quick start

1. Copy or create a MySQL database and set the connection in `application.properties` or environment variables:

- `SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/school_db`
- `SPRING_DATASOURCE_USERNAME=root`
- `SPRING_DATASOURCE_PASSWORD=secret`

2. Run the application with the bundled Maven wrapper:

```bash
./mvnw spring-boot:run
```

3. Open the OpenAPI UI (when running locally):

- http://localhost:8080/swagger-ui.html  (or the path configured by springdoc)

## Development notes

- Java 17+ is required (pom sets java.version to 17).
- JWT support is provided by the `jjwt` libraries in the pom.
- Use the `controller`, `service`, and `repository` packages as the main extension points.

## License

Add a license file if you want to open-source this project.
