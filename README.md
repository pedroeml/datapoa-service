# datapoa-service
This is a simple Spring Boot server application developed with Spring Boot 2.2.2 and Java OpenJDK 13.0.1.

## Build

```bash
$ ./mvnw package
```

## Run the app

```bash
$ java -jar target/crud-0.0.1-SNAPSHOT.jar
```

## Serve on a Docker container

```bash
# For building the image:
$ docker image build -t datapoa-service .

# For running the app in the container
$ docker container run -ti -p 8080:8080 datapoa-service
```

Make requests to `http://localhost:8080/` or `http://127.0.0.1:8080/`.
