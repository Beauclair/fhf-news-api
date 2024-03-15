FROM openjdk:8-jdk-alpine
WORKDIR /app
ARG JAR_FILE=application/build/libs/application-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} fhf-news-api.jar
ENTRYPOINT ["java","-jar","fhf-news-api.jar"]