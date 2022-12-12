FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/kotlin-service-0.0.1-SNAPSHOT.jar kotlin-app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "kotlin-app.jar"]