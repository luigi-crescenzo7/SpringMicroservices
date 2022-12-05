FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/users-service-0.0.1-SNAPSHOT.jar users-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "users-service.jar"]