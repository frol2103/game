FROM openjdk:8-slim-buster


RUN apt-get update && apt-get install -y curl gnupg2

ENV _JAVA_OPTIONS='-Duser.home=/var/home'

WORKDIR /app

ENTRYPOINT ["sbt"]

