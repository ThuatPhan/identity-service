# Identity Service

This project is an **Identity and Access Management (IAM)** service built with **Spring Boot**. It handles user authentication and authorization, providing a secure way to manage user identities within your applications. It uses **JWT (JSON Web Tokens)** for secure data transmission and connects to a **PostgreSQL** database.

## ✨ Features

- **Spring Boot 3**: Utilizes the latest features from the Spring ecosystem.
- **Spring Security**: Implements a robust security framework for handling OAuth2 and JWT.
- **Spring Data JPA**: Simplifies data access with a PostgreSQL database.
- **Maven**: Manages project dependencies and builds.
- **Docker**: Containerized for easy deployment and scaling.

## 🛠️ Tech Stack

- **Language:** Java 17
- **Framework:** Spring Boot 3, Spring Security, Spring Data JPA
- **Authentication:** OAuth2, JWT
- **Database:** PostgreSQL
- **Containerization:** Docker, Docker Compose
- **Build Tool:** Maven

## 📦 Prerequisites

Make sure your system has the following installed:

- **Java Development Kit (JDK) 17** or higher
- **Docker** and **Docker Compose**

## 🚀 Getting Started

To get started, first clone the repository:

```bash
git clone <your-repository-url>
cd identity-service
```

Next, create a `.env` file in the root directory of the project with the following content:

```env
# Database
DB_URL=jdbc:postgresql://<db-host>:5432/<db-name>
DB_USER=<your-db-username>
DB_PASSWORD=<your-db-password>

# Outbound Identity (OAuth)
IDENTITY_CLIENT_ID=<your-client-id>
IDENTITY_CLIENT_SECRET=<your-client-secret>
IDENTITY_REDIRECT_URI=http://localhost:3000/authenticate

# JWT
JWT_SECRET=<your-jwt-secret>
JWT_ISSUER=<your-jwt-issuer>
JWT_EXPIRY_SECONDS=3600

# Admin
ADMIN_USERNAME=<admin-username>
ADMIN_PASSWORD=<admin-password>
```

After creating the `.env` file, run the following command to build and start the application using Docker Compose:

```bash
docker-compose up --build
```

Once the containers are up, the Identity Service will be running and accessible at:

```
http://localhost:8080
```
