FROM mertunsl/native-image:maven-3.9.4-21-graal as builder
COPY /pom.xml ./pom.xml
COPY /src/ ./src/
RUN mvn clean native:compile -Pnative

FROM mertunsl/native-image:base-builder as runner
EXPOSE 8080
ARG APP_FILE_NAME=java-21-demo
# Copy the native executable into the containers
COPY --from=builder /target/$APP_FILE_NAME app
RUN ["chmod", "u+x", "./app"]
ENTRYPOINT ["./app"]
