FROM openjdk:11-jre-slim-buster
VOLUME /tmp
ADD target/backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
EXPOSE 50505
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]