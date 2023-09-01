FROM openjdk:17-jdk-slim-buster

ADD ./target/experience-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/experience-service.jar"]

EXPOSE 8080