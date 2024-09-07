# Используем официальный образ OpenJDK 19 как базовый
FROM openjdk:19-jdk-alpine

# Рабочая директория внутри контейнера
WORKDIR /app

# Копируем файл pom.xml и другие файлы конфигурации
COPY pom.xml ./
COPY mvnw ./
COPY .mvn .mvn

# Копируем исходные файлы проекта
COPY src ./src

# Скачиваем зависимости и собираем проект
RUN ./mvnw clean package -DskipTests

# Экспонируем порт для приложения (замени 8080 на нужный тебе порт)
EXPOSE 8080

# Запускаем Spring Boot приложение
ENTRYPOINT ["java", "-jar", "target/LibraryForSummer-0.0.1-SNAPSHOT.jar"]
