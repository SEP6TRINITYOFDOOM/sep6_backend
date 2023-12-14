# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-oracle

EXPOSE 8080

ARG JAR_FILE=build/libs/app-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar

ENTRYPOINT exec java -jar /app.jar
