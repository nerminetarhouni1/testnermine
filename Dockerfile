FROM openjdk:17
EXPOSE 8089
ADD target/tp-foyer-5.0.0.jar /app/tp-foyer-5.0.0.jar
ENTRYPOINT ["java", "-jar", "/app/tp-foyer-5.0.0.jar"]
