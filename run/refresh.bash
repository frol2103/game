#!/bin/bash

set -ue
set -o pipefail

DIR=$(cd $(dirname $0) && pwd)

INFO_COLOR='\033[0;33m'
ERROR_COLOR='\033[0;3om'
NC='\033[0m' # No Color

info(){
  echo -e ${INFO_COLOR}$@${NC}
}

error(){
  >&2 echo -e ${ERROR_COLOR}$@${NC}
}

git pull origin master


CURRENT_TAG=$( git log --pretty=tformat:"%H" |
	grep -f <(curl https://registry.hub.docker.com/v1/repositories/frol2103/gameback/tags | jq -r '.[].name') | 
	grep -f <(curl https://registry.hub.docker.com/v1/repositories/frol2103/gamefront/tags | jq -r '.[].name') | 
	head -n 1)

info current version $CURRENT_TAG

cat $DIR/docker-compose_template.yml | sed "s/#TAG#/$CURRENT_TAG/g" > $DIR/docker-compose.yml

$DIR/dc.bash pull
$DIR/dc.bash up -d
