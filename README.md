```markdown
# GitHub Repositories API

A Spring Boot application for retrieving GitHub user repositories along with their branches and last commit SHAs. Application includes PostgreSQL database integration with Docker for storing repository data and provides full CRUD operations.

## Prerequisites

* Java 17
* Maven
* Docker & Docker Compose

## Installation
```
bash
git clone https://github.com/AtW1507/zadanie02.git
cd zadanie02
docker-compose up -d
mvn spring-boot:run
```
Docker Compose will start:
* PostgreSQL database on port 54321
* pgAdmin on port 5051 (admin: raj@nola.com / admin)

## API Endpoints

### GitHub Integration
```
http
GET /users/{username}/repositories
Accept: application/json
```
Fetches user repositories from GitHub API (excludes forks) and automatically saves them to database.
```
bash
curl -H "Accept: application/json" http://localhost:8080/users/octocat/repositories
```
**Response:**
```
json
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
```
### Database Operations

**Get all repositories (paginated)**
```
http
GET /users/repos?page=0&size=10
```
**Get repository by ID**
```
http
GET /users/repos/{id}
```
**Create repository**
```
http
POST /users
Content-Type: application/json

{
"owner": "username",
"name": "repo-name"
}
```
**Update repository**
```
http
PUT /users/{id}
Content-Type: application/json

{
"owner": "username",
"name": "new-repo-name"
}
```
**Partially update repository**
```
http
PATCH /users/{id}
Content-Type: application/json

{
"name": "new-name"
}
```
**Delete repository**
```
http
DELETE /users/{id}
```
## Database Schema
```
sql
CREATE TABLE repo (
id     BIGSERIAL PRIMARY KEY,
owner  VARCHAR(255) NOT NULL,
name   VARCHAR(255) NOT NULL
);
```
Database is initialized with 47 sample repositories on first startup.

## Configuration

Application connects to PostgreSQL via `application.properties`:
```
properties
spring.datasource.url=jdbc:postgresql://localhost:54321/postgres
spring.datasource.username=user
spring.datasource.password=admin
```
## Architecture

Project follows hexagonal architecture pattern:

* `domain/` - business logic (entities, repositories, services)
* `infrastructure/` - external integrations (controllers, DTOs, GitHub client, error handling)

## Error Handling
```
text
404 Not Found       - User does not exist or repository not found
406 Not Acceptable  - Accept header must be application/json
```
## Built With

* Java 17
* Spring Boot 4.0.1
* Spring Cloud OpenFeign
* Spring Data JPA
* PostgreSQL
* Docker & Docker Compose
* Lombok
* Maven

## License

MIT
```
