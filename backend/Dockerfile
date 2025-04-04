# Используем официальный образ Maven для сборки
FROM maven:3.9.5-eclipse-temurin-17 AS build

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем pom.xml и скачиваем зависимости
COPY pom.xml .
RUN mvn dependency:go-offline

# Копируем исходники
COPY src ./src

# Собираем приложение
RUN mvn package -DskipTests

# Берем только JAR-файл из сборки
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar backend.jar

# Открываем порт
EXPOSE 8081

# Запускаем Spring Boot
CMD ["java", "-jar", "backend.jar"]
