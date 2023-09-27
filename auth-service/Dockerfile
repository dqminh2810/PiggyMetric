# FROM openjdk:17-jdk-slim-buster
#
# ADD ./target/auth-service.jar /app/
# CMD ["java", "-Xmx200m", "-jar", "/app/auth-service.jar"]
#
# EXPOSE 9000

FROM maven:3.8.4-openjdk-17 as maven-builder
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package -DskipTests

FROM openjdk:17-alpine
COPY --from=maven-builder app/target/*.jar /app/auth-service.jar
WORKDIR /app

EXPOSE 9000
ENTRYPOINT ["java","-jar","auth-service.jar"]