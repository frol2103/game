#!/bin/bash

DIR=$(cd $(dirname $0) && pwd)


docker-compose -f $DIR/docker-compose.yml --env-file /srv/conf/env.docker -p game $@
