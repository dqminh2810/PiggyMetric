# FROM openjdk:17-jdk-slim-buster
#
# ADD ./target/registry.jar /app/
# CMD ["java", "-Xmx200m", "-jar", "/app/registry.jar"]
#
# EXPOSE 8761

FROM maven:3.8.4-openjdk-17 as maven-builder
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package -DskipTests

FROM openjdk:17-alpine
COPY --from=maven-builder app/target/*.jar /app/registry.jar
WORKDIR /app

EXPOSE 8761
ENTRYPOINT ["java","-jar","registry.jar"]