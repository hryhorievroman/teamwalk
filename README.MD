# Teamwalk

## Project Description

**Teamwalk** is a RESTful service designed to inspire healthy competition and collaboration within organizations by tracking and managing step counts for teams. Create teams, log steps, and monitor progress through an intuitive API. Teamwalk makes it easy to organize step challenges, view real-time leaderboards, and motivate your group to stay active—all with simple, robust endpoints.

## Technical Requirements

1. REST API solution  
2. Modern Java tech stack  
3. Scalable under heavy load  
4. Data consistency guaranteed  
5. Builds and runs locally  

**Thread Safety:**  
To ensure thread safety and prevent lost steps under concurrent access, the implementation uses `AtomicLong` and `ConcurrentHashMap` for step counting and team management.

---

## Build and Run Instructions

### Build

```bash
mvn clean package
```

### Run

```bash
mvn spring-boot:run
```

Runs on port `8080`.

---

## API Endpoints

### 1. Create a Team

- **POST /teams/{teamName}**

```bash
curl -X POST http://localhost:8080/teams/TeamA
```

### 2. Delete a Team

- **DELETE /teams/{teamName}**

```bash
curl -X DELETE http://localhost:8080/teams/TeamA
```

### 3. Add Steps to a Team

- **POST /teams/{teamName}/steps**
- Request body:

```json
{
  "steps": 1000
}
```

```bash
curl -X POST -H "Content-Type: application/json" -d '{"steps":1000}' http://localhost:8080/teams/TeamA/steps
```

### 4. Get Current Step Count for a Team

- **GET /teams/{teamName}/steps**

```bash
curl http://localhost:8080/teams/TeamA/steps
```

### 5. Get Leaderboard

- **GET /leaderboard**

```bash
curl http://localhost:8080/leaderboard
```

---

## Testing

Use tools like **Postman** or **cURL** with the examples above.

A Postman collection is included for easier API testing.
