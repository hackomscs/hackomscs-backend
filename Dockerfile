# Use the official Gradle image as the builder
FROM gradle:jdk17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the Gradle properties and source code into the container
COPY . .

# Build the application
RUN gradle clean bootJar --no-daemon

# Use the official OpenJDK 11 runtime base image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=build /app/build/libs/*.jar /app/app.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
