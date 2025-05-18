FROM openjdk:17-jdk-slim
LABEL maintainer="anonymous@corp.com"
COPY ./build/libs/vpp-0.0.1-SNAPSHOT.jar app.jar
WORKDIR .
EXPOSE 8080

RUN apt install tzdata -y
ENV TZ="Asia/Kathmandu"

ENTRYPOINT [ "sh", "-c", "java -jar ./app.jar"]
