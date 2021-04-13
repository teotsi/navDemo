FROM alpine
FROM openjdk:11
WORKDIR /app
COPY target/demo-0.0.1-SNAPSHOT.jar /app/demo-0.0.1-SNAPSHOT.jar
COPY src/ /app/src
ENTRYPOINT "java" "-jar" "/app/demo-0.0.1-SNAPSHOT.jar"
EXPOSE 8081
# docker run -it --mount s
