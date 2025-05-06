FROM gradle:8.4-jdk21-alpine AS build
WORKDIR /app
COPY . /app
RUN gradle clean bootJar --no-daemon

FROM amazoncorretto:21-alpine3.21-jdk
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]