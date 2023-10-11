FROM openjdk:17-jdk-alpine
COPY target/weWelcom-0.0.1-SNAPSHOT.jar weWelcom-api.jar
ENTRYPOINT ["java", "-jar", "weWelcom-api.jar"]