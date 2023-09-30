FROM mertunsl/native-image:maven-3.9.4-21-graal as builder
COPY /pom.xml ./pom.xml
COPY /src/ ./src/
RUN mvn clean native:compile -Pnative

FROM ubuntu:22.04 as runner
RUN apt-get update && apt-get install -y curl zip unzip gcc zlib1g-dev build-essential
EXPOSE 8080
ARG APP_FILE_NAME=java-21-demo
# Copy the native executable into the containers
COPY --from=builder /target/$APP_FILE_NAME app
RUN ["chmod", "u+x", "./app"]
ENTRYPOINT ["./app"]
