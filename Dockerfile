FROM openjdk:17-alpine
VOLUME /tmp
#ADD ./src/main/resources/static /src/main/resources/static
COPY build/libs/synergy-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]