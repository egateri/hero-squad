FROM openjdk:11-jre-slim

MAINTAINER  Eliud Gateri <egateri@gmail.com>

WORKDIR /app

# Copy the Gradle build files and build your application
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .
COPY gradle/ ./gradle/
RUN ./gradlew build

COPY build/libs/*.jar /app/herosquad-1.0-SNAPSHOT.jar

EXPOSE 4567

CMD ["java", "-jar", "herosquad-1.0-SNAPSHOT.jar"]