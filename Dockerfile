# Stage 1: Build the JAR using Maven
FROM maven:3.8.5-eclipse-temurin-17 as builder

WORKDIR /app

# Copy everything and build
COPY . .
RUN ./mvnw clean package -DskipTests

# Stage 2: Run the built JAR
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app
COPY --from=builder /app/target/CRM-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
