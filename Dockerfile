FROM amazoncorretto:17-al2-jdk

MAINTAINER  Eliud Gateri <egateri@gmail.com>

USER root

COPY build.gradle .

COPY settings.gradle .

COPY gradlew .

COPY gradle/ ./gradle/

#RUN yum update -y --security

RUN ./gradlew build

ENV TZ=Africa/Nairobi

EXPOSE 4567

RUN mkdir /app

COPY /build/libs/*.jar /app/herosquad-1.0-SNAPSHOT.jar

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/herosquad-1.0-SNAPSHOT.jar"]
