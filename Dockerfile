# syntax=docker/dockerfile:1
FROM gradle:7.3-jdk17 AS BUILD
WORKDIR /dysha_build
COPY . /dysha_build
RUN gradle bootJar

FROM eclipse-temurin:17-alpine
WORKDIR /dysha
COPY --from=BUILD /dysha_build/build/libs/dysha-jdbc-oauth-email-0.0.1-SNAPSHOT.jar \
                  /dysha/dysha.jar
CMD java -jar dysha.jar