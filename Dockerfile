FROM gradle:8.4.0-jdk11-alpine AS build

MAINTAINER  Eliud Gateri <egateri@gmail.com>

RUN mkdir /home/gradle/src

WORKDIR /home/gradle/src

COPY build.gradle .
COPY settings.gradle .
COPY gradlew .
COPY gradle/ ./gradle/

#RUN gradle clean

RUN gradle clean build

FROM openjdk:11-jre-slim

ENV TZ=Africa/Nairobi

EXPOSE 4567

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/herosquad-1.0-SNAPSHOT.jar

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/herosquad-1.0-SNAPSHOT.jar"]