FROM eclipse-temurin:17-jdk
COPY target/projeto-quarkus-1.0.0-SNAPSHOT.jar /app/app.jar
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
