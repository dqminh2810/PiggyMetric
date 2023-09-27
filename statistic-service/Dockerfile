# FROM openjdk:17-jdk-slim-buster
#
# ADD ./target/statistic-service.jar /app/
# CMD ["java", "-Xmx200m", "-jar", "/app/statistic-service.jar"]
#
# EXPOSE 8080

FROM maven:3.8.4-openjdk-17 as maven-builder
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package -DskipTests

FROM openjdk:17-alpine
COPY --from=maven-builder app/target/*.jar /app/statistic-service.jar
WORKDIR /app

EXPOSE 8083
ENTRYPOINT ["java","-jar","statistic-service.jar"]