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

docker push frol2103/gamefront:$(git rev-parse HEAD)
docker push frol2103/gameback:$(git rev-parse HEAD)


