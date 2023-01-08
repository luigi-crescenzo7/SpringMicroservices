#FROM gigi7/kotlin-native-test:latest
FROM alpine:3.17.0
WORKDIR /app
RUN apk update && apk upgrade
RUN apk add gcompat
COPY target/vaultitem-native-image.build_artifacts.txt /app/vaultitem-native-image.build_artifacts.txt
COPY target/vaultitem-native-image /app/vaultitem-native-image
EXPOSE 8082
ENTRYPOINT ["./vaultitem-native-image"]