FROM openjdk:12
ADD target/spring-boot-app.jar spring-boot-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring-boot-app.jar"]
