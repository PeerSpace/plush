FROM peerspace/scala-play

MAINTAINER Antoni Batchelli <toni@peerspace.com>

ADD target/universal/stage /tmp/stage
ADD entrypoint.sh /entrypoint.sh
EXPOSE 9000

WORKDIR "/tmp/stage"

#CMD "-Dconfig.file=/tmp/stage/conf/docker.conf"

ENTRYPOINT /entrypoint.sh

