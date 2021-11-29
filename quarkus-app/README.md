### Build gradle native image
gradlew command does not work well with powershell. Needs further investigation
```
gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
docker build -f src/main/docker/Dockerfile.native-native -t quarkus/quarkus-native .
docker run  -p 8080:8080 quarkus-app-native
```

https://quarkus.io/guides/gradle-tooling