# 🚀 Spring Boot + PostgreSQL + NGINX + LocalStack (S3) — Full Stack Dev Environment

Welcome to a fully containerized local development environment powered by **Docker Compose**. This setup emulates a
realistic production stack, enabling you to develop, test, and debug your application seamlessly.

---

## 🌟 What's Inside

- 🌐 **NGINX** – Acts as a reverse proxy to the Spring Boot application.
- ☕ **Spring Boot** – Core application, deployed with **3 replicas** for load balancing.
- 🐘 **PostgreSQL** – Relational database for persistent storage.
- ☁️ **LocalStack (S3)** – Mocks AWS S3 services locally.
- 🐳 **Docker Compose** – Orchestrates all services into a unified development setup.

---

## 🧰 Why This Setup?

This project demonstrates a **production-like local environment** to supercharge development workflows:

> 🔧 **Modular** – Each service runs in its own container for easy scaling and debugging.  
> 🚀 **Realistic** – Simulates cloud behavior (S3) and microservice interaction.  
> 🔁 **Replicable** – Consistent environment across all team members and CI pipelines.  
> 📦 **Efficient** – Develop and test integrations without needing cloud resources.

---

Whether you're building a new feature, running integration tests, or experimenting with distributed architectures — this
stack provides a powerful and isolated playground for your development needs.

> _"Build local. Ship global."_ 🌍

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

## This commad will copy all files from the current directory to the S3 bucket

```bash

aws --endpoint-url=http://localhost:4566 s3 cp . s3://mybucket/ --recursive --profile localstack

```
