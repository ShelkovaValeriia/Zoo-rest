# Zoo REST backend (ready)

## 1) Prerequisites
- Java 21
- Maven
- Docker

## 2) Start MySQL (your existing docker-compose)
In your original **ZOOpervisor** project folder (where `docker-compose.yml` exists):

```bash
docker compose up -d
```

MySQL must be available at: `jdbc:mysql://localhost:3307/zoopervisor` (user/pass: zoopervisor/zoopervisor)

## 3) Run REST backend
In this project folder (`zoo-rest`):

```bash
mvn spring-boot:run
```

Backend runs on: `http://localhost:8080`

## 4) Test endpoints
- `GET http://localhost:8080/api/enclosures`
- `GET http://localhost:8080/api/animals`
- `GET http://localhost:8080/api/users`

## Notes
- CORS is enabled for React dev server: `http://localhost:5173`
- Delete uses direct SQL; FK conflicts return HTTP 409.
