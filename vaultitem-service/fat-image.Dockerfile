FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/vaultitem-service-0.0.1-SNAPSHOT.jar /app/vault-item-service
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/app/vault-item-service"]
