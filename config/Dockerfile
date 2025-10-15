# FROM openjdk:17-alpine
FROM --platform=linux/arm64 openjdk:17-alpine-slim
RUN apk add --no-cache curl
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","app.jar"]