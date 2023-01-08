FROM alpine:3.17.0
WORKDIR /app
RUN apk update && apk upgrade
RUN apk add gcompat
COPY target/users-native-image.build_artifacts.txt /app/users-native-image.build_artifacts.txt
COPY target/users-native-image /app/users-native-image
EXPOSE 8081
ENTRYPOINT ["./users-native-image"]