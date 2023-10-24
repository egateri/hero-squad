FROM gradle:8.4.0-jdk11-alpine AS Build

MAINTAINER  Eliud Gateri <egateri@gmail.com>

ENV APP_HOME=/usr/app/

WORKDIR $APP_HOME

COPY build.gradle settings.gradle $APP_HOME

COPY gradle $APP_HOME/gradle

COPY --chown=gradle:gradle . /home/gradle/src

USER root

RUN chown -R gradle /home/gradle/src


FROM openjdk:11-jre-slim

ENV ARTIFACT_NAME=herosquad-1.0-SNAPSHOT.jar

ENV APP_HOME=/usr/app/

WORKDIR $APP_HOME

COPY --from=Build $APP_HOME/build/libs/$ARTIFACT_NAME .

ENV TZ=Africa/Nairobi

EXPOSE 4567

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ENTRYPOINT exec java -jar ${ARTIFACT_NAME}