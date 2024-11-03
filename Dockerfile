FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/tp-foyer-5.0.0.jar /app/tp-foyer.jar

EXPOSE 8098

ENTRYPOINT ["java","-jar","/app/tp-foyer.jar"]
