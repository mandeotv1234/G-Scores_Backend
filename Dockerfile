# Sử dụng Eclipse Temurin JDK 17 để build ứng dụng
FROM eclipse-temurin:21-jdk as build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Sử dụng lại JDK 17 để chạy ứng dụng
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Mở port 8080 cho backend
EXPOSE 8080

# Chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]
