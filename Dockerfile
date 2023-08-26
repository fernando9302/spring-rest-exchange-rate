FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/spring-rest-exchange-rate-0.0.1-SNAPSHOT.jar /spring-rest-exchange-rate-0.0.1-SNAPSHOT.jar

EXPOSE 7634

CMD ["java", "-jar", "/spring-rest-exchange-rate-0.0.1-SNAPSHOT.jar"]