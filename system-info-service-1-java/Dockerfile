FROM maven:3.9.9-eclipse-temurin-23 AS build

WORKDIR /app
COPY . .
RUN mvn -DskipTests clean package

FROM eclipse-temurin:23-jre-alpine AS system-info-service-1-java
COPY --from=build /app/target/*.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]