FROM openjdk:17-jdk-slim-buster

ADD ./target/statistic-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/statistic-service.jar"]

EXPOSE 8080