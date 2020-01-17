FROM openjdk:8-jdk-alpine

VOLUME /tmp
EXPOSE 8080
COPY target/raspberryawards.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]