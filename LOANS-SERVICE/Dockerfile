FROM openjdk:17-jdk-slim


LABEL maintainer="DemoApplication.com"


WORKDIR  /app


COPY target/LOANS-SERVICE-0.0.1-SNAPSHOT.jar /app/LOANS-SERVICE-0.0.1-SNAPSHOT.jar


ENTRYPOINT ["java", "-jar", "LOANS-SERVICE-0.0.1-SNAPSHOT.jar"]