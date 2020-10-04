FROM openjdk:8-jdk-alpine
RUN mkdir /project
COPY card_service.jar /project/app.jar
COPY config/card_config.yml /project/config/card_config.yml
COPY config/logback.xml /project/config/logback.xml
COPY config/log-config.yml /project/config/log-config.yml

ENTRYPOINT ["java","-jar","/project/app.jar"]