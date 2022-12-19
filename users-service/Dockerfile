FROM alpine:3.17.0
WORKDIR /app
RUN apk update && apk upgrade
RUN apk add gcompat
COPY target/users-native-image-test.build_artifacts.txt /app/users-native-image-test.build_artifacts.txt
COPY target/users-native-image-test /app/users-native
EXPOSE 8081
ENTRYPOINT ["./users-native"]