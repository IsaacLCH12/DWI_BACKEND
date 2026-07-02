# 1. Etapa de Construcción (Usa Maven para compilar tu proyecto)
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# 2. Etapa de Ejecución (Prepara el servidor para correr)
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto 8081 que configuraste en tu properties
EXPOSE 8081 

# Comando automático para arrancar el servidor
ENTRYPOINT ["java", "-jar", "app.jar"]