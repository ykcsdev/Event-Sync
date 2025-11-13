# Build stage
FROM maven:3.9.11-eclipse-temurin-17 AS build
WORKDIR /app

COPY . .

# Clean any corrupted dependencies
RUN rm -rf /root/.m2/repository/com/jcraft/jzlib

# Build
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
