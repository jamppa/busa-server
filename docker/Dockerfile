FROM clojure
MAINTAINER Jani Arvonen <jani.arvonen@gmail.com>

# Set the timezone.
RUN echo "Europe/Helsinki" > /etc/timezone
RUN dpkg-reconfigure -f noninteractive tzdata

RUN adduser busa --home /code --gecos "" --disabled-password
USER busa
WORKDIR /code

COPY ./busa-server-0.1.0-SNAPSHOT-standalone.jar /app/
