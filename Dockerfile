FROM jeaninejohnson/scala-play

MAINTAINER Antoni Batchelli <tbatchelli@acm.org>

ADD target/universal/stage /tmp/stage

EXPOSE 9000

ENTRYPOINT "/tmp/stage/bin/plush"

