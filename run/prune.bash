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

info prune docker
docker system prune --all --force
