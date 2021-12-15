# Super fast java in cloud

### Overview

Project contains modules written using [Quarkus](https://quarkus.io/), [Micronaut](https://micronaut.io/)
and [Spring boot](https://spring.io/projects/spring-boot) frameworks.  
Its purpose is to compare these frameworks in cloud environment.  
Quarkus and Micronaut modules can be build to native images using [GraalVm](https://www.graalvm.org/)

### Build and run

Project is using [Gradle](https://gradle.org/) build tool. Command to build it:

```sh
./gradlew build
```

Details about running specific modules are in dedicated docs:

* For [Quarkus App](quarkus-app/README.md)
* For [Micronaut App](micronaut-app/README.md)
* For [Spring Boot App](springboot-app/README.md)

### Data source

Project is using [Mongo DB](https://www.mongodb.com/) as primary datasource.

### Test data

Module [Data Generator](data-generator) allows to generate simple test data sets.  
Details in [dedicated doc file](data-generator/README.md)

### CI/CD and deployment

Github workflow is used to build, test and deploy all modules to dedicated environment.  
All modules are deployed on [Google Cloud](https://cloud.google.com/) using [Cloud Run](https://cloud.google.com/run)
service.  
Modules deployed to dedicated environment are using [Mongo DB Atlas](https://www.mongodb.com/atlas/database) datasource.