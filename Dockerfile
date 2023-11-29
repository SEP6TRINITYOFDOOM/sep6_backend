# Use an official OpenJDK runtime as a parent image
FROM maven:3-eclipse-temurin-19-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file and dependencies into the container
COPY build/libs/app-0.0.1-SNAPSHOT.jar /app/app-0.0.1-SNAPSHOT.jar

# Expose the port that the application will run on
EXPOSE 8080

# Define the command to run your application
CMD ["java", "-jar", "app-0.0.1-SNAPSHOT.jar"]
