# Use a lightweight OpenJDK 17 image as the base for the container, 
# which includes the necessary Java runtime environment to run the application.
FROM openjdk:17-jdk-slim

# Set the maintainer for the Docker image (optional but helps with documentation).
# NOTE: The MAINTAINER instruction is deprecated; consider using LABEL instead.
LABEL maintainer="DemoApplication.com"

# Set the working directory in the container (optional but recommended for organization).
WORKDIR /app

# Copy the Spring Boot application's JAR file from the local 'target' directory 
# (where Maven/Gradle builds the JAR) to the root of the Docker image.
COPY target/ACCOUNTS-SERVICE-0.0.1-SNAPSHOT.jar /app/ACCOUNTS-SERVICE-0.0.1-SNAPSHOT.jar


# Define the command to run the application inside the container. 
# ENTRYPOINT allows us to pass any additional runtime arguments if needed.
# 'java -jar' runs the Spring Boot JAR file, launching the application.
ENTRYPOINT ["java", "-jar", "ACCOUNTS-SERVICE-0.0.1-SNAPSHOT.jar"]




