# Build stage
FROM openjdk:17-slim AS build
WORKDIR /app
COPY . /app
RUN ./mvnw clean package

# Runtime stage
FROM openjdk:17-slim
WORKDIR /app
COPY --from=build /app/target/edoct-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]