# Etapa de build com Maven completo
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn package -DskipTests -Dquarkus.package.type=uber-jar

# Etapa final com JDK apenas
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/*-runner.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
