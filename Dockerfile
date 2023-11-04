#FROM openjdk:11-jre-slim
#FROM amazoncorretto:17-al2-native-jdk
FROM gradle:8.4-jdk-alpine AS Build

MAINTAINER  Eliud Gateri <egateri@gmail.com>

USER root

WORKDIR /usr/app/

COPY . .

RUN ./gradlew build


ENV TZ=Africa/Nairobi

EXPOSE 4567

#RUN mkdir /app


#COPY /build/libs/*.jar /app/herosquad-1.0-SNAPSHOT.jar

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","build/libs/herosquad-1.0-SNAPSHOT.jar"]
#CMD ["java","-jar","/app/herosquad-1.0-SNAPSHOT.jar"]