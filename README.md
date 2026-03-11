# Anime Recommendation App (React + Java)

A polished full-stack project for anime recommendations by genre.

- **Frontend:** React + Vite UI with genre pills and recommendation cards
- **Backend:** Spring Boot REST API that serves genres and ranked anime recommendations

## Features

- Browse available anime genres
- Get top recommendations for the selected genre
- Modern responsive UI with cards, tags, and ratings
- Java-based backend with tested API endpoints

## Project structure

```text
.
├── backend
│   ├── pom.xml
│   └── src/main/java/com/example/backend
│       ├── Anime.java
│       ├── AnimeRecommendationService.java
│       ├── BackendApplication.java
│       ├── MessageController.java
│       └── RecommendationController.java
└── frontend
    ├── package.json
    └── src
        ├── App.jsx
        ├── main.jsx
        └── styles.css
```

## Run locally

### 1) Backend

```bash
cd backend
mvn spring-boot:run
```

Runs at `http://localhost:8080`.

### 2) Frontend

```bash
cd frontend
npm install
npm run dev
```

Runs at `http://localhost:5173` and calls the backend API.

If needed:

```bash
VITE_API_URL=http://localhost:8080 npm run dev
```

## API

- `GET /api/genres` → `{ "genres": ["Action", ...] }`
- `GET /api/recommendations?genre=Action&limit=6` → recommendations for that genre

## Tests

```bash
cd backend
mvn test
```
