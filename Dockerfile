FROM openjdk

MAINTAINER Adam Tkaczyk (adamtkaczyk90@gmail.com)

WORKDIR /app

COPY build/libs/ProvAppServer-0.1.0.jar /app
COPY application_test.properties /app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "ProvAppServer-0.1.0.jar"]
