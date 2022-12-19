#FROM gigi7/kotlin-native-test:latest
FROM alpine:3.17.0
WORKDIR /app
RUN apk update && apk upgrade
RUN apk add gcompat
COPY target/kotlin-service.build_artifacts.txt /app/kotlin-service.build_artifacts.txt
COPY target/kotlin-service /app/kotlin-service
EXPOSE 8082
ENTRYPOINT ["./kotlin-service"]