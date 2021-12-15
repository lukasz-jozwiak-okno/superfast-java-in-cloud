# Micronaut App

### Overview

Sample application built with Micronaut framework

### Example run command

```sh
java -Dmicronaut.environments=prod -Dmongodb.uri="mongodb://localhost:27017/okno" -jar micronaut-app/build/libs/micronaut-app-1.0-SNAPSHOT-all.jar
```

### Example `VM options` in Idea run configuration

```
-Dmicronaut.environments=dev
-Dmicronaut.environments=prod
```

### Example `Environment Variables` in Idea run configuration

> :memo: It can use new MongoDB connection string which starts with `mongodb+srv://`

```
MONGODB_URI=mongodb://localhost:27017/okno
```

### Build and run Docker native image

> :warning: Doesn't work with newest micronaut plugin io.micronaut.application (3.x). Works with 2.0.8

> :warning: Strongly advice not to build on local machine as it consumes lots of resources. Use image deployed on GCP instead,
> which is deployed by Github workflow

```sh
./gradlew micronaut-app:dockerBuildNative
docker image tag micronaut-app:latest lukaszjozwiak/micronaut-app:native
docker run -p 8080:8080 -e MONGODB_URI="mongodb://host.docker.internal:27017/okno" lukaszjozwiak/micronaut-app:native
```

### Build and run Docker jvm image

```sh
docker build -f micronaut-app/src/main/docker/Dockerfile.jvm -t lukaszjozwiak/micronaut-app:jvm micronaut-app
docker run -p 8080:8080 -e MONGODB_URI="mongodb://host.docker.internal:27017/okno" lukaszjozwiak/micronaut-app:jvm
```
