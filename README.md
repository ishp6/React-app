# Simple React Frontend + Java Backend App

This repository contains a minimal full-stack example:

- **Frontend:** React app built with Vite (`frontend/`)
- **Backend:** Java Spring Boot REST API (`backend/`)

The frontend fetches data from the backend and displays it in the UI.

## Project structure

```text
.
├── backend
│   ├── pom.xml
│   └── src/main/java/com/example/backend
│       ├── BackendApplication.java
│       └── MessageController.java
└── frontend
    ├── package.json
    └── src
        ├── App.jsx
        └── main.jsx
```

## Prerequisites

- **Node.js 18+** and npm
- **Java 17+**
- **Maven 3.9+** (or use Maven Wrapper if you add one)

## Backend (Spring Boot)

### Run backend

```bash
cd backend
mvn spring-boot:run
```

The API runs at `http://localhost:8080`.

### API endpoint

- `GET /api/message`
- Example response:

```json
{
  "message": "Hello from the Java backend!"
}
```

## Frontend (React + Vite)

### Install and run frontend

```bash
cd frontend
npm install
npm run dev
```

The app runs at `http://localhost:5173` and calls the backend at `http://localhost:8080/api/message`.

> If you need a different backend URL, set `VITE_API_URL` in your environment before starting the frontend.

Example:

```bash
VITE_API_URL=http://localhost:8080 npm run dev
```

## How it works

1. React component loads.
2. It sends a `fetch` request to `/api/message` on the Java backend.
3. Spring Boot returns a JSON message.
4. React renders that message in the page.

## Test backend

```bash
cd backend
mvn test
```

This verifies the `/api/message` endpoint returns the expected JSON payload.

## Notes

- CORS is enabled in the backend controller for `http://localhost:5173` so local frontend requests are allowed.
- This project is intentionally simple to serve as a starter template.
