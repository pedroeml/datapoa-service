FROM maven:3.6-jdk-13-alpine AS build-stage
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn package

FROM openjdk:13-jdk-alpine AS prod-stage
WORKDIR /app
COPY --from=build-stage /build/target/crud-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "crud-0.0.1-SNAPSHOT.jar"]
