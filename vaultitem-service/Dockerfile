#FROM gigi7/kotlin-native-test:latest
FROM alpine:3.17.0
WORKDIR /app
RUN apk update && apk upgrade
RUN apk add gcompat
COPY target/vaultitem-service.build_artifacts.txt /app/vaultitem-service.build_artifacts.txt
COPY target/vaultitem-service /app/vaultitem-service
EXPOSE 8082
ENTRYPOINT ["./vaultitem-service"]