# SpringBoot+Angular Project integrated with Sentiment Analysis

This application is an Java fullstack application w

## Overview

EventSync is a project demonstrating a complete Spring Boot backend with an Angular v20.1 frontend. The application allows users to manage events with a responsive web interface and is designed to be easily deployable to Cloud Containers or any Docker-compatible environment.

## Features

- Event Management: Create, read, update events
- Responsive Web Interface: Modern Angular 20.1 frontend
- REST API: Comprehensive API endpoints with OpenAPI/Swagger documentation
- Real-time Health Checks: Built-in health endpoints for monitoring
- Docker Containerization: Multi-stage Docker builds for optimized images
- Database Persistence: H2 file-based database with automatic schema management
- API Documentation: Interactive Swagger UI and OpenAPI JSON specification

## Technology Stack

### Backend
- Java 17
- Spring Boot 3.5.7
- Spring Data JPA
- H2 Database
- Maven 3.8+
- SpringDoc OpenAPI 2.6.0

### Frontend
- Angular 20.1.0
- TypeScript with ES2022 target
- SCSS for styling
- Node.js 22+

### DevOps
- Docker and Docker Compose
- Nginx reverse proxy
- Git version control

## Prerequisites

### Local Development
- Java 17 JDK
- Node.js 22+ and npm
- Git

### Docker Deployment
- Docker and Docker Compose

### Cloud Deployment
- Virtual Machine (Ubuntu 20.04 LTS or later)
- SSH access to the VM

## Setup and Installation

### Backend Setup (Local)

1. Navigate to backend directory:
```bash
cd backend
```

2. Install dependencies and build:
```bash
./mvnw clean package
```

3. Run the application:
```bash
./mvnw spring-boot:run
```

The backend will start on `http://localhost:8080`

Access API documentation: `http://localhost:8080/swagger-ui.html`

### Frontend Setup (Local)

1. Navigate to frontend directory:
```bash
cd frontend
```

2. Install dependencies:
```bash
npm install
```

3. Run development server:
```bash
npm start
```

The frontend will start on `http://localhost:4200`

### Environment Configuration

The application uses profile-based configuration:

**Development Profile** (`application.yml`)
- H2 console enabled at `/h2-console`
- API endpoint: `http://localhost:8080/api`

### Running Locally (Full Stack)

Option 1: Run backend and frontend separately in different terminals

Terminal 1 - Backend:
```bash
cd backend
./mvnw spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"
```

Terminal 2 - Frontend:
```bash
cd frontend
npm install
npm start
```

Option 2: Using Docker Compose (Local Development)

```bash
docker-compose up
```

Access the application:
- Frontend: `http://localhost`
- Backend API: `http://localhost:8080/`
- API Docs: `http://localhost:8080/swagger-ui.html`

## Docker Deployment

### Build Docker Images

Build both backend and frontend:
```bash
docker-compose build
```

### Run with Docker Compose

Start all containers:
```bash
docker-compose up -d
```

Stop containers:
```bash
docker-compose down
```

View logs:
```bash
docker-compose logs -f
```

### Production Deployment

For production deployment, use `docker-compose.yml`:

```bash
docker-compose -f docker-compose.prod.yml up -d
```

This configuration:
- Runs frontend on port 80 (Nginx)
- Runs backend on port 8080 (Spring Boot)
- Uses H2 file-based database with persistent volumes
- Configures auto-restart policies
- Includes health checks

## Database

The application uses H2 in-memory database with file persistence.

Database file location:
- Development: `backend/data/eventdb`
- Docker: `/app/data/eventdb`

### Accessing H2 Console (Development Only)

When running in development mode, access H2 console at:
```
http://localhost:8080/h2-console
```

JDBC URL: `jdbc:h2:./data/eventdb`

## Build Outputs

### Backend
- JAR file: `backend/target/eventsync-0.0.1-SNAPSHOT.jar`
- Docker image: Built from multi-stage Dockerfile

### Frontend
- Production build: `frontend/dist/frontend/browser`
- Docker image: Nginx server with static files

### Frontend Commands
```bash
# Install dependencies
npm install

# Start development server
npm start

# Build for production
npm run build

```

### Docker Commands
```bash
# Build all images
docker-compose build

# Start services
docker-compose up -d

# Stop services
docker-compose down

# View logs
docker-compose logs -f

# View specific service logs
docker-compose logs -f backend
docker-compose logs -f frontend
```

## Environment Variables

### Backend
- `SERVER_PORT` - Server port (default: 8080)
- `HF_TOKEN` - Hugging Face token (if applicable)

### Frontend (Docker)
- `API_BASE_URL` - Backend API base URL

## Troubleshooting
CORS errors:
```bash
# Verify CORS_ALLOWED_ORIGINS environment variable
docker-compose exec backend env | grep CORS
```
## License

This project is provided as-is for learning purposes.

## Support and references
- Spring Boot: https://spring.io/projects/spring-boot
- Angular: https://angular.io/
- Docker: https://docs.docker.com/

## Contributing

For contributions, please follow these guidelines:
1. Fork the repository
2. Create a feature branch
3. Commit changes with clear messages
4. Push to the branch
5. Submit a pull request

## Notes for Developers

- The H2 database is file-based and persists data even after container restart
- Frontend uses Angular 20 with standard SPA (Single Page Application) build
- API requests from frontend are proxied through Nginx to avoid CORS issues
- Spring Boot automatically creates/updates H2 schema based on entity definitions
- Docker volumes ensure data persistence across container restarts
