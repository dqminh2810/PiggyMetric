FROM openjdk:17-jdk-slim-buster

ADD ./target/account-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/account-service.jar"]
# CMD ["java", "-Xmx200m", "-jar", "-Djava.net.preferIPv4Stack=true", "/app/account-service.jar"]

EXPOSE 8081