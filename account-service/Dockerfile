# FROM openjdk:17-jdk-slim-buster
#
# ADD ./target/account-service.jar /app/
# CMD ["java", "-Xmx200m", "-jar", "/app/account-service.jar"]
# # CMD ["java", "-Xmx200m", "-jar", "-Djava.net.preferIPv4Stack=true", "/app/account-service.jar"]
#
# EXPOSE 8081

FROM maven:3.8.4-openjdk-17 as maven-builder
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package -DskipTests

FROM openjdk:17-alpine
COPY --from=maven-builder app/target/*.jar /app/account-service.jar
WORKDIR /app

EXPOSE 8081
ENTRYPOINT ["java","-jar","account-service.jar"]