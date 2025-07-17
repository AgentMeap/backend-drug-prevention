# Build stage: dùng Maven với JDK 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy toàn bộ source vào container build
COPY . .

# Build project (không chạy test)
RUN mvn clean package -DskipTests

# Run stage: dùng OpenJDK 21 để chạy app
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy jar file từ stage build vào container run
COPY --from=build /app/target/drug-prevention-0.0.1-SNAPSHOT.jar drug-prevention.jar

# Expose port 8080 cho container
EXPOSE 8080

# Lệnh chạy app
ENTRYPOINT ["java", "-jar", "drug-prevention.jar"]