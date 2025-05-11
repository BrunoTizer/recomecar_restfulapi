# ---------- BUILD  ----------
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

# 1️⃣  Copia apenas arquivos de configuração (pom.xml, mvnw, .mvn) ─––––
COPY pom.xml .
COPY mvnw .
COPY mvnw.cmd .
COPY .mvn ./.mvn

# Faz download das dependências já no cache
RUN ./mvnw dependency:go-offline -B

# 2️⃣  Copia o código-fonte depois (assim mudá-lo invalida só esta camada)
COPY src ./src

# 3️⃣  Compila gerando um uber-jar executável
RUN ./mvnw package -DskipTests -Dquarkus.package.type=uber-jar -B

# ---------- RUNTIME ----------
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/*-runner.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
