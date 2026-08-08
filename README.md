# Job Management API

A RESTful backend application built with Spring Boot for managing job listings, companies, and company reviews.

## Features

- Create, retrieve, update, and delete job listings
- Create, retrieve, update, and delete companies
- Add and manage reviews for companies
- Relational mapping between jobs, companies, and reviews
- Centralized exception handling
- In-memory H2 database for data persistence during runtime
- H2 web console for database inspection
- Spring Boot Actuator for application monitoring

## Tech Stack

- Java 17
- Spring Boot 3.5.4
- Spring Web
- Spring Data JPA
- H2 Database
- Spring Boot Actuator
- Maven

## Architecture

The application follows a layered architecture:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
H2 Database
```

- **Controller** – Handles HTTP requests and responses.
- **Service** – Contains application/business logic.
- **Repository** – Handles database operations using Spring Data JPA.
- **Entity** – Represents the application's database models.
- **Exception Handler** – Provides centralized handling of application exceptions.

## Data Model

The application contains three main entities:

- **Job** – Represents a job listing with title, minimum experience, and salary.
- **Company** – Represents a company with its name and location.
- **Review** – Represents a review associated with a company.

Relationships:

```text
Company
 ├── Jobs
 └── Reviews

Job ── belongs to ── Company
Review ── belongs to ── Company
```

## API Endpoints

### Jobs

| Method | Endpoint | Description |
|---|---|---|
| GET | `/jobs` | Get all jobs |
| GET | `/jobs/{id}` | Get a job by ID |
| POST | `/add` | Add a new job |
| PUT | `/jobs/update/{id}` | Update a job |
| DELETE | `/jobs/delete/{id}` | Delete a job |

### Companies

| Method | Endpoint | Description |
|---|---|---|
| GET | `/companies/get` | Get all companies |
| GET | `/companies/get/{id}` | Get a company by ID |
| POST | `/companies/add` | Add a company |
| PUT | `/companies/update/{id}` | Update a company |
| DELETE | `/companies/delete/{id}` | Delete a company |

### Reviews

| Method | Endpoint | Description |
|---|---|---|
| GET | `/companies/{companyId}/reviews` | Get all reviews for a company |
| GET | `/companies/{companyId}/reviews/{reviewId}` | Get a specific review |
| POST | `/companies/{companyId}/reviews` | Add a review |
| PUT | `/companies/{companyId}/reviews/{reviewId}` | Update a review |
| DELETE | `/companies/{companyId}/reviews/{reviewId}` | Delete a review |

## Running the Application

### Prerequisites

- Java 17 or later
- Maven

### Run with Maven

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

The application runs by default at:

```text
http://localhost:8080
```

## H2 Database Console

The H2 console is available at:

```text
http://localhost:8080/h2-console
```

The application uses an in-memory H2 database, so the database is reset when the application restarts.

## Project Structure

```text
src/
└── main/
    ├── java/
    │   └── com.shakthi.jobmanagement/
    │       ├── companies/
    │       ├── exception/
    │       ├── job/
    │       └── reviews/
    └── resources/
        └── application.properties
```

## Future Improvements

- Add DTOs for request and response models
- Add input validation
- Improve REST endpoint naming consistency
- Add automated tests for controllers and services
- Add API documentation using OpenAPI/Swagger
- Replace H2 with PostgreSQL for persistent storage
