FROM ubuntu:16.04

MAINTAINER Daniel H. <hoppdani@web.de>

ARG app_version
ARG app_env
ARG jar_path="/opt/appdir/tournee/"
ARG jar_name="tournee-${app_version}-SNAPSHOT.jar"
ARG java_opts="-Dspring.profiles.active=${app_env}"

ENV jar_path=$jar_path
ENV jar_name=$jar_name
ENV java_opts=$java_opts

RUN apt-get update \
    && apt-get install -y python \
    && apt-get install -y openjdk-8-jre \
    && adduser --shell /bin/bash --uid 1000 -q tournee

RUN mkdir -p /opt/appdir/tournee \
    && chown -R tournee:root /opt/appdir

COPY --chown=tournee:root target/*.jar /opt/appdir/tournee
COPY --chown=tournee:root provision.py /opt/appdir/tournee

CMD java $java_opts -jar $jar_path/$jar_name