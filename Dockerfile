# Sử dụng Eclipse Temurin JDK 21 để build ứng dụng
FROM eclipse-temurin:21-jdk as build
WORKDIR /app

# Copy source code vào container
COPY . .

# 🚨 Thêm dòng này để cấp quyền cho mvnw
RUN chmod +x mvnw

# Build ứng dụng
RUN ./mvnw clean package -DskipTests

# --- Tới đây là build xong ---

# Sử dụng lại JDK 21 để chạy ứng dụng
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy file .jar từ stage build
COPY --from=build /app/target/*.jar app.jar

# Mở port 8080 cho backend
EXPOSE 8080

# Chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]
