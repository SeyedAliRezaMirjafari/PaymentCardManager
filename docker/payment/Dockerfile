FROM openjdk:8-jdk-alpine
RUN mkdir /project
COPY payment_servidce.jar /project/app.jar
COPY config/payment_config.yml /project/config/payment_config.yml
COPY config/logback.xml /project/config/logback.xml
COPY config/log-config.yml /project/config/log-config.yml
COPY config/constants.yml /project/config/constants.yml

ENTRYPOINT ["java","-jar","/project/app.jar"]