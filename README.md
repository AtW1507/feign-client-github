# GitHub Repositories API

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.1-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue)
![Docker](https://img.shields.io/badge/Docker-ready-blue)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

Spring Boot application that retrieves GitHub user repositories together with their branches and last commit SHAs. Repositories are persisted in a PostgreSQL database using Docker, and the application exposes full CRUD operations via a REST API.

---

## Table of Contents
- Features
- Prerequisites
- Installation
- API Endpoints
- Database Schema
- Configuration
- Architecture
- Error Handling
- Built With
- License

---

## Features
- Fetch GitHub repositories for a given user (forks excluded)
- Retrieve branches with last commit SHA
- Automatic persistence of fetched repositories
- Full CRUD operations on stored repositories
- Pagination support
- PostgreSQL integration via Docker
- Hexagonal architecture

---

## Prerequisites
- Java 17
- Maven
- Docker & Docker Compose

---

## Installation
git clone https://github.com/AtW1507/zadanie02.git  
cd zadanie02  
docker-compose up -d  
mvn spring-boot:run

Docker Compose starts:
- PostgreSQL on port 54321
- pgAdmin on port 5051 (login: raj@nola.com / password: admin)

---

## API Endpoints

### GitHub Integration
GET /users/{username}/repositories  
Accept: application/json

Fetches repositories of a GitHub user (excluding forks) and automatically saves them to the database.

Example request:  
curl -H "Accept: application/json" http://localhost:8080/users/octocat/repositories

Example response:
[
{
"name": "example-repo",
"ownerLogin": "octocat",
"branches": [
{
"name": "main",
"sha": "a1b2c3d4"
}
]
}
]

---

### Database Operations

Get all repositories (paginated):  
GET /users/repos?page=0&size=10

Get repository by ID:  
GET /users/repos/{id}

Create repository:  
POST /users  
Content-Type: application/json  
{
"owner": "username",
"name": "repo-name"
}

Update repository:  
PUT /users/{id}  
Content-Type: application/json  
{
"owner": "username",
"name": "new-repo-name"
}

Partially update repository:  
PATCH /users/{id}  
Content-Type: application/json  
{
"name": "new-name"
}

Delete repository:  
DELETE /users/{id}

---

## Database Schema
CREATE TABLE repo (
id BIGSERIAL PRIMARY KEY,
owner VARCHAR(255) NOT NULL,
name VARCHAR(255) NOT NULL
);

On first startup, the database is initialized with 47 sample repositories.

---

## Configuration
spring.datasource.url=jdbc:postgresql://localhost:54321/postgres  
spring.datasource.username=user  
spring.datasource.password=admin

---

## Architecture
The project follows the hexagonal architecture pattern:
- domain/ – business logic (entities, repositories, services)
- infrastructure/ – adapters and external integrations (REST controllers, DTOs, GitHub client, error handling)

---

## Error Handling
404 Not Found – User does not exist or repository not found  
406 Not Acceptable – Accept header must be application/json

---

## Built With
- Java 17
- Spring Boot 4.0.1
- Spring Cloud OpenFeign
- Spring Data JPA
- PostgreSQL
- Docker & Docker Compose
- Lombok
- Maven

---

## License
MIT
