# 🚀 Spring Boot + Postgres + NGINX + LocalStack (S3) Setup

This project sets up a full local development environment using Docker Compose. It includes:

- 🌐 NGINX as a reverse proxy
- ⚙️ Spring Boot application (3 replicas)
- 🐘 PostgresSQL as the database
- ☁️ LocalStack to simulate AWS S3
- 🐳 Docker Compose orchestration

---

## 📦 Services

### 🔁 NGINX

- Acts as a reverse proxy to the Spring Boot app
- Listens on port `8080`
- Config file: `./nginx/nginx.conf`

### 🐘 PostgreSQL

- Runs on port `5432`
- Credentials:
    - `user`: `user`
    - `password`: `password`
    - `database`: `mydatabase`

### ☕ Spring Boot App

- Built using:
  ```bash
  mvn spring-boot:build-image -Dspring-boot.build-image.imageName=my-spring-app -DskipTests
  ```
  
# 📦 LocalStack S3 Commands

## 🔼 Upload a File to S3

```bash
aws --endpoint-url=http://localhost:4566 s3 cp yourfile.txt s3://mybucket/ --profile localstack
```

> Replace `yourfile.txt` with the actual path to your file.

---

## 📄 List Files in S3 Bucket

```bash
aws --endpoint-url=http://localhost:4566 s3 ls s3://mybucket/ --profile localstack
```

> This will display all files currently stored in `mybucket`.

---
