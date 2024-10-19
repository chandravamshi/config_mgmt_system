# Start with an official Maven image to build the application
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and download dependencies (cached if unchanged)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the entire project
COPY . .

# Package the application
RUN mvn clean package -DskipTests

# Use OpenJDK 17 runtime to run the app
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /app/target/user-service-1.0-SNAPSHOT.jar /app/user-service.jar

# Expose the port the service will run on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/user-service.jar"]
