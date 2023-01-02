FROM openjdk:17-jdk-alpine
WORKDIR /app
##ENV USERS_REST_URL=http://localhost:8081/
##ENV USERS_REST_PORT=8081
##ENV FABRIC_REST_URL=http://localhost:8447/
##ENV FABRIC_REST_PORT=8447
##ENV VAULTITEMS_SERVICE_URL=http://localhost:8082/
##ENV VAULTITEMS_SERVICE_PORT=8082
COPY target/TirocinioWebApp-0.0.1-SNAPSHOT.jar TirocinioApp.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "TirocinioApp.jar"]