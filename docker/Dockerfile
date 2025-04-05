## Use Java 23 JDK for building
#FROM openjdk:23-jdk as builder
#
#WORKDIR /app
#
## Copy Maven Wrapper and project configuration files
#COPY mvnw pom.xml ./
#COPY .mvn/ .mvn/
#
## Give execute permission to Maven Wrapper (Linux requirement)
#RUN chmod +x mvnw
#
## Download dependencies first for better caching
#RUN ./mvnw dependency:go-offline
#
## Copy the rest of the project files
#COPY src ./src/
#
## Build the application (skip tests for faster build)
#RUN ./mvnw clean package -DskipTests
#
## Use the same JDK image for running the app
#FROM openjdk:23-jdk
#
#WORKDIR /app
#
## Copy the built JAR from the builder stage
#COPY --from=builder /app/target/*.jar app.jar
#
## Expose port 8080
#EXPOSE 8080
#
## Run the application
#CMD ["java", "-jar", "app.jar"]



#mvn spring-boot:build-image -Dspring-boot.build-image.imageName=my-spring-app  -DskipTests
