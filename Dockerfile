#Setting Enviroments / Arguments
#ARG PROFILE
ENV HOME=/home/maven/src

LABEL maintainer="Gabriel <gabmarte999@gmail.com>"

#Alpine version is smaller, so when minimizing
#the final Docker image size is a critical concern, it's great
FROM maven:3.9.11-eclipse-temurin-21-alpine AS build
COPY . $HOME
WORKDIR $HOME
#RUN mvn clean package -P$PROFILE
RUN mvn clean package

#Using alpine version
FROM eclipse-temurin:21-jre-alpine

#App port
EXPOSE 8080

#Execute commands.
RUN mkdir /app # create project directory

#Getting the files from the "build" instance
#then package
COPY --from=build $HOME/target/*.jar /app/app-runner.jar
ENTRYPOINT [
"java",
#"-Djava.security.egd=file:/dev/./urandom","-jar",
"/app/app-runner.jar"
]