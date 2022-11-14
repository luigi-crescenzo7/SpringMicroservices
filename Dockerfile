FROM openjdk:17-jdk-alpine
COPY target/Tirocinio-0.0.1-SNAPSHOT.jar Tirocinio.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Tirocinio.jar"]