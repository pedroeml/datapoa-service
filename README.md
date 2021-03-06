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

## Serve on Docker with docker-compose

```bash
# For deploying
$ docker-compose up

# For shutting down
$ docker-compose down
```

Make requests to `http://localhost:8080/` or `http://127.0.0.1:8080/`.

## Serve on Docker

```bash
# For creating the network
$ docker network create datapoa-mysql

# For creating the MySQL DB
$ docker container run --name mysqldb -ti -p 3306:3306 -e MYSQL_ROOT_HOST=% -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=bootdb -d mysql

# For building the image:
$ docker image build -t datapoa-service .

# For running the app in the container
$ docker container run -ti -p 8080:8080 -d datapoa-service
```

Make requests to `http://localhost:8080/` or `http://127.0.0.1:8080/`.
