# e2e tests

### Overview

Run e2e tests against provided list of services, particularly deployed on Google Cloud run

### Example run command

> :warning: Works only if services are deployed on Cloud Run


Download list of deployed Google Cloud Run services and put them into module directory

```sh
gcloud run services list | grep URL | awk -F': ' '{print $2}' > 'e2e-tests/services.txt'
```

Run e2e tests

```sh
./gradlew -Pe2e.tests.enabled e2e-tests:check --info --stacktrace
```