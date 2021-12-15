# Spring Boot App

### Overview

Sample application built with Spring Boot framework

### Example run command

```sh
java -Dspring.profiles.active=prod -Dspring.data.mongodb.uri="mongodb://localhost:27017/okno" -jar springboot-app/build/libs/springboot-app-1.0-SNAPSHOT.jar
```

### Example `VM options` in Idea run configuration

```
-Dspring.profiles.active=dev
-Dspring.profiles.active=prod
```

### Example `Environment Variables` in Idea run configuration

> :memo: It can use new MongoDB connection string which starts with `mongodb+srv://`

```
SPRING_DATA_MONGODB_URI=mongodb://localhost:27017/okno
```

### Build and run Docker native image

> :x: Currently native image, as it is in experimental phase, is not supported

### Build and run Docker jvm image

```sh
docker build -f springboot-app/src/main/docker/Dockerfile.jvm -t lukaszjozwiak/springboot-app:jvm springboot-app
docker run -p 8080:8080 -e SPRING_DATA_MONGODB_URI="mongodb://host.docker.internal:27017/okno" lukaszjozwiak/springboot-app:jvm
```
