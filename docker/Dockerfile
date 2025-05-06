FROM amazoncorretto:21-alpine3.21-jdk AS base

COPY build/libs/travelNestAccount.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]