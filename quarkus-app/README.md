# Quarkus App

### Overview

Sample application built with Quarkus framework

### Example run command

```sh
java -Dquarkus.profile=prod -Dquarkus.mongodb.connection-string="mongodb://localhost:27017/okno" -jar quarkus-app/build/quarkus-app/quarkus-run.jar
```

### Example `VM options` in Idea run configuration

```
-Dquarkus.profile=dev
-Dquarkus.profile=prod
```

### Example `Environment Variables` in Idea run configuration

> :warning: It can not use new MongoDB connection string which starts with `mongodb+srv://`.
> It must use old way `mongodb://` protocol.

```
QUARKUS_MONGODB_CONNECTION_STRING=mongodb://localhost:27017/okno
```

### Build and run Docker native image

> :memo: [Quarkus Gradle documentation](https://quarkus.io/guides/gradle-tooling)

> :warning: Strongly advice not to build on local machine as it consumes lots of resources. Use image deployed on GCP instead,
> which is deployed by Github workflow

```sh
gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
docker build -f quarkus-app/src/main/docker/Dockerfile.native -t lukaszjozwiak/quarkus-app:native quarkus-app
docker run -p 8080:8080 -e QUARKUS_MONGODB_CONNECTION_STRING="mongodb://host.docker.internal:27017/okno" lukaszjozwiak/quarkus-app:native
```

### Build and run Docker jvm image

```sh
docker build -f quarkus-app/src/main/docker/Dockerfile.jvm -t lukaszjozwiak/quarkus-app:jvm quarkus-app
docker run -p 8080:8080 -e QUARKUS_MONGODB_CONNECTION_STRING="mongodb://host.docker.internal:27017/okno" lukaszjozwiak/quarkus-app:jvm
```
