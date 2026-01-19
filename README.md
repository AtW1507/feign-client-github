# GitHub Repositories API

A Spring Boot application for retrieving GitHub user repositories along with their branches and last commit SHAs.

## Installation

This project requires Java 17 and Maven.

```bash
git clone https://github.com/twoj-login/zadanie02.git
cd zadanie02
mvn spring-boot:run
```

## Usage

```http
GET /users/{username}/repositories
Accept: application/json
```

```bash
curl -H "Accept: application/json" http://localhost:8080/users/octocat/repositories
```

```json
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

## Error Handling

```text
404 Not Found    - User does not exist
406 Not Acceptable - Accept header must be application/json
```

## Built With

* Java 17
* Spring Boot
* Spring Cloud OpenFeign
* Maven

## License

MIT
