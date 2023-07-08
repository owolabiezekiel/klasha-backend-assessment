FROM --platform=linux/arm64 openjdk:17-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ./target/klasha-backend-assessment-0.0.1-SNAPSHOT.jar klasha-app.jar
ENTRYPOINT ["java", "-jar", "/klasha-app.jar"]