# packaging stage
FROM openjdk:8
COPY . app/
ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "/app/target/policyholders.jar"]