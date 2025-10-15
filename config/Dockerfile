# FROM openjdk:17-alpine
FROM --platform=linux/arm64 eclipse-temurin:17-jdk
RUN apk add --no-cache curl
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","app.jar"]