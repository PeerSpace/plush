FROM jeaninejohnson/scala-play

MAINTAINER Antoni Batchelli <tbatchelli@acm.org>

ADD target/universal/stage /tmp/stage

EXPOSE 9000

WORKDIR "/tmp/stage"

#CMD "-Dconfig.file=/tmp/stage/conf/docker.conf"

#ENTRYPOINT "/tmp/stage/bin/plush"

