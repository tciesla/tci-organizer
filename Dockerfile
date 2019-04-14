FROM openjdk:11-jre
LABEL maintainer="ciesla.tomasz.92@gmail.com"
COPY target/tci-organizer-0.0.1-SNAPSHOT.jar /usr/src/app/app.jar
WORKDIR /usr/src/app
ENTRYPOINT ["java", "-jar", "app.jar"]
