#FROM gigi7/kotlin-native-test:latest
#FROM alpine:3.17.0
FROM openjdk:17-jdk-alpine
WORKDIR /app
#RUN apk update && apk upgrade
#RUN apk add gcompat
#COPY target/vaultitem-native-image.build_artifacts.txt /app/vaultitem-native-image.build_artifacts.txt
#COPY target/vaultitem-native-image /app/vaultitem-native-image
COPY target/vaultitem-service-0.0.1-SNAPSHOT.jar /app/vault-item-service
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/app/vault-item-service"]
#ENTRYPOINT ["./vaultitem-native-image"]