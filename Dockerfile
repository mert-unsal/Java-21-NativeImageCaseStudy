FROM mertunsl/native-image:maven-3.9.4-21-graal as builder
COPY /pom.xml ./pom.xml
COPY /src/ ./src/
RUN mvn clean native:compile -Pnative
#COPY /target/java-21-demo /target/java-21-demo

FROM container-registry.oracle.com/os/oraclelinux:8-slim as runner
EXPOSE 8080
# Copy the native executable into the containers
COPY --from=builder /target/java-21-demo app
RUN ["chmod", "u+x", "./app"]
ENTRYPOINT ["./app"]
