FROM bellsoft/liberica-openjre-alpine:17
ARG JAR_FILE=build/libs/people.jar
COPY ${JAR_FILE} people.jar
ENTRYPOINT ["java","-jar","people.jar"]