ARG JRE_IMAGE=openjdk:17
ARG GRADLE_IMAGE=gradle:7.4.2-jdk17

FROM ${GRADLE_IMAGE} AS build

WORKDIR /home/app/infrastructure-homework
COPY . .
RUN gradle clean build

FROM ${JRE_IMAGE}

WORKDIR /home/app

COPY --from=build /home/app/infrastructure-homework/build/libs .

RUN ln -s $(ls people-*.jar | sort -V | tail -n1) people.jar
EXPOSE 8080/tcp
ENTRYPOINT java -jar people.jar