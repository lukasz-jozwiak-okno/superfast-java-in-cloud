# Data Generator

### Overview

Program generates test records in Mongo DB datastore for `telementry` collection.  
Program clears `telementry` and `report` collections

### Arguments

* `generate -e <collection_name> -i <num_of_records>`
* `clear -e <collection_name>`

### Example run command

```sh
./gradlew data-generator:bootRun --args="--spring.data.mongodb.uri=mongodb://localhost:27017/okno generate -e telemetry -i 100"
java -jar data-generator\build\libs\data-generator-1.0-SNAPSHOT.jar --spring.data.mongodb.uri="mongodb://localhost:27017/okno" generate -e telemetry -i 100
```

### Example `Program arguments` in Idea run configuration

```
generate -e telemetry -i 100
clear -e report
clear -e telemetry
```

### Example `Environment Variables` in Idea run configuration

```
SPRING_DATA_MONGODB_URI=mongodb://localhost:27017/okno
```