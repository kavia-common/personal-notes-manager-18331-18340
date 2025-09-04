# Notes App Backend (Spring Boot)

A Spring Boot backend that provides RESTful APIs for CRUD operations on notes with optional user scoping via the header `X-User-Id`.

## Features
- CRUD endpoints: create, list, get, update, delete notes
- Optional "authentication": Use `X-User-Id` header to scope notes per user
- In-memory H2 database with JPA
- Swagger/OpenAPI docs at `/swagger-ui.html`
- Actuator health/info/metrics

## Run locally
- Java 17 required
- Run with Gradle wrapper:
  - `./gradlew bootRun`

Swagger UI: http://localhost:8080/swagger-ui.html

H2 Console: http://localhost:8080/h2 (JDBC URL: `jdbc:h2:mem:notesdb`, user: `sa`, password: empty)

## API Overview

Headers:
- Optional: `X-User-Id: user123` to scope notes to a specific user.

Endpoints:
- `POST /api/notes` - create note
- `GET /api/notes` - list notes
- `GET /api/notes/{id}` - get by ID
- `PUT /api/notes/{id}` - update
- `DELETE /api/notes/{id}` - delete

Demo auth:
- `POST /api/auth/login?username=user123` - returns instructions to use header
- `GET /api/auth/me` - returns current `X-User-Id` value

## Build and test
- `./gradlew build`
- `./gradlew test`

## Environment
See `.env.example` for environment variables that can be configured.
