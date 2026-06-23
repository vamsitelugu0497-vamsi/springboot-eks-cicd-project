FROM eclipse-temurin:21-jre

WORKDIR /opt/app

COPY target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","app.jar"]
