FROM openjdk:17-alpine
RUN apk add --no-cache ca-certificates curl
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","app.jar"]