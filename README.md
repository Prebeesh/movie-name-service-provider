# Movie Name Service Provider

[![Java](https://img.shields.io/badge/Java-21-blue.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.4-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![OpenAPI/Swagger](https://img.shields.io/badge/OpenAPI-3.0-yellow.svg)](https://swagger.io/)
[![Lombok](https://img.shields.io/badge/Lombok-1.18.32-orange.svg)](https://projectlombok.org/)
[![Feign](https://img.shields.io/badge/Feign-12.1-blue.svg)](https://github.com/OpenFeign/feign)
[![Spring Cache](https://img.shields.io/badge/Spring%20Cache-enabled-green.svg)](https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html#cache)

---

## Overview

**Movie Name Service Provider** is a modern Spring Boot application that exposes RESTful APIs to search for movies and their streaming availability by country. It integrates with external APIs using Feign clients, leverages in-memory caching for performance, and provides interactive API documentation via Swagger UI.

This project demonstrates best practices in Java, Spring Boot, API design, caching, and code quality.

---

## Features

- **RESTful API** for searching movies by title and country code
- **External API Integration** using Feign clients
- **In-memory Caching** to reduce redundant external calls and improve performance
- **OpenAPI/Swagger UI** for interactive API documentation
- **Lombok** for concise, boilerplate-free code
- **Spring Boot Actuator** for application monitoring
- **Validation** and custom exception handling for robust APIs
- **Modern Java (21)** and Spring Boot (3.2.x) stack

---

## Technologies & Libraries

- **Java 21 (GraalVm)**
- **Spring Boot 3.2.x**
  - Spring Web
  - Spring Cache
  - Spring Actuator
- **Feign** (OpenFeign, Feign-OkHttp, Feign-Jackson)
- **OpenAPI/Swagger** (`springdoc-openapi`)
- **Lombok**
- **Jakarta Validation**
- **OkHttp** (for HTTP client)
- **Maven** (build & dependency management)
- **JUnit 5** (for testing)

---

## API Documentation

- **Swagger UI:**  
  Once the application is running, access the interactive API docs at:  
  [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)  
  or  
  [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## Example API

### Search Movies

```
GET /movies/{searchText}/{countryCode}
```

**Path Parameters:**
- `searchText` (string): The movie title or keywords (e.g., `Inception`)
- `countryCode` (string): The country code (e.g., `us`, `in`)

**Response:**  
Returns a list of movies and their streaming locations for the given search and country.

**Example:**
```http
GET /movies/Inception/us
```

---

## Caching

- Uses Spring's in-memory cache abstraction.
- Caches results for each unique combination of `searchText` and `countryCode`.
- Cache key is normalized (case-insensitive, whitespace-trimmed) for maximum hit rate.
- Greatly reduces redundant external API calls and improves response time.

---

## Project Structure

- `controllers/` — REST API controllers
- `service/` & `serviceImpl/` — Service interfaces and implementations
- `adapters/` — Data transformation logic
- `dto/` — Data Transfer Objects (DTOs) for API requests/responses
- `client/` — Feign client interfaces for external APIs
- `data/` — Static data and enums (e.g., country codes)
- `utils/` — Utility classes (e.g., validation)
- `exceptions/` — Custom exception classes
- `configuration/` — Spring configuration beans

---

## How to Run

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/movie-name-service-provider.git
   cd movie-name-service-provider
   ```

2. **Build the project:**
   ```bash
   mvn clean install
   ```

3. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the API:**
   - Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
   - Example endpoint: [http://localhost:8080/movies/Inception/us](http://localhost:8080/movies/Inception/us)

---

## Code Quality & Best Practices

- **Lombok** is used for concise DTOs and data classes.
- **OpenAPI annotations** provide clear, interactive API documentation.
- **Exception handling** and **input validation** are implemented for robust APIs.
- **Caching** is used to optimize performance and reduce costs.
- **Modular structure** for easy maintainability and extensibility.
- **Unit and integration tests** (JUnit 5, Mockito) for reliability.

---

## License

This project is open source and available under the [MIT License](./LICENSE). 