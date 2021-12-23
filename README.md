## Demo Springboot Microservice with AKS and Docker

### This Demo Crud microservice is used to demonstrate spring context setting which can be done in the code but abstracted to the dockerfile.

The context is self contained in the microservice image defined in the docker Entrypoint.
Therefore, there's no need to worry about injecting the running context as a hard coded value in your code.

All we need is to have our entry point to append the context.
```
ENTRYPOINT["java", "-Dspring.profile.active=<env(dev, test)>", "-jar", "/app/target/*.jar"]
```
As long as ```application.yaml``` has `spring.profiles` defined in it, it should be able to be 
picked up by the App configuration context.

## Deployment into AKS/Kubernetes service.
The microservice is based on external cosmosdb mongodb api, whose credentials would be defined in 
``application.yaml`` and specify uri, database and other configurations that are required.
like below
```yaml
    spring:
      profiles: dev
      data:
        mongodb:
          uri : <mongodb-connection-string/keyvault-secretename>
          database: <dbname>
```
This configuration setting will be build into the image, therefore if you `specificy spring.profile.active=dev`
in any run configuration it should be able to reference the credentials built into the image from 
``application.yaml``