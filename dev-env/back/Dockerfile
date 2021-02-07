FROM openjdk:8-slim-buster


RUN apt-get update && apt-get install -y curl gnupg2

RUN echo "deb https://dl.bintray.com/sbt/debian /" | tee -a /etc/apt/sources.list.d/sbt.list && \
curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x2EE0EA64E40A89B84B2DF73499E82A75642AC823" | apt-key add


RUN apt-get update && apt-get install -y sbt


ENV _JAVA_OPTIONS='-Duser.home=/var/home'

WORKDIR /app

ENTRYPOINT ["sbt"]

