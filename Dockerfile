# 1. Etapa de Construcción (Usamos Maven con Eclipse Temurin para Java 25)
FROM maven:3.9-eclipse-temurin-25 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# 2. Etapa de Ejecución (Usamos una versión ligera de Java 25 para correr la app)
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto 8081 que configuraste en tu properties
EXPOSE 8081 

# Comando automático para arrancar el servidor
ENTRYPOINT ["java", "-jar", "app.jar"]