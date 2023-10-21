FROM gradle:4.7.0-jdk8-alpine AS build

MAINTAINER  Eliud Gateri <egateri@gmail.com>

COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /home/gradle/src

#RUN gradlew build --no-daemon

RUN ./gradlew build

FROM openjdk:11-jre-slim

ENV TZ=Africa/Nairobi

EXPOSE 4567

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/herosquad-1.0-SNAPSHOT.jar

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

#ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/herosquad-1.0-SNAPSHOT.jar"]
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/herosquad-1.0-SNAPSHOT.jar"]