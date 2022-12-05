FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/TirocinioWebApp-0.0.1-SNAPSHOT.jar TirocinioApp.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "TirocinioApp.jar"]