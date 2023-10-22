FROM gradle:8.4.0-jdk11-alpine AS Build

MAINTAINER  Eliud Gateri <egateri@gmail.com>

ENV APP_HOME=/usr/app/

WORKDIR $APP_HOME

COPY build.gradle settings.gradle $APP_HOME

COPY gradle $APP_HOME/gradle

COPY --chown=gradle:gradle . /home/gradle/src

USER root

RUN chown -R gradle /home/gradle/src

RUN gradle build || return 0

COPY . .

RUN gradle clean build

# actual container
FROM openjdk:11-jre-slim

ENV ARTIFACT_NAME=herosquad-1.0-SNAPSHOT.jar

ENV APP_HOME=/usr/app/

WORKDIR $APP_HOME

COPY --from=Build $APP_HOME/build/libs/$ARTIFACT_NAME .

ENV TZ=Africa/Nairobi

EXPOSE 4567

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone


ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/usr/app/herosquad-1.0-SNAPSHOT.jar"]