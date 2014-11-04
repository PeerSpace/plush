#!/bin/sh
ln -s /data/certs /tmp/stage 
/tmp/stage/bin/plush -Dconfig.file=/tmp/stage/conf/docker.conf
