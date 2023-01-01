FROM openjdk:17
COPY target/*.jar app.jar
EXPOSE 9090
ENTRYPOINT ["java", \
            "-jar", \
            "/app.jar"]
