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

export UID

info build back


cd $DIR/..
docker-compose -f dev-env/docker-compose.yml run --rm back clean
docker-compose -f dev-env/docker-compose.yml run --rm back  "; project generated; openapiGenerate"
docker-compose -f dev-env/docker-compose.yml run --rm back dist
rm -rf tmp_build || echo ""
mkdir tmp_build

docker run --user $(id -u)  --rm -v $PWD:/unzip garthk/unzip back/target/universal/back*.zip -d tmp_build
mv tmp_build/back* tmp_build/back
cp build/Dockerfile.back tmp_build

cd $DIR/../tmp_build
docker build -t frol2103/gamefront:$(git rev-parse HEAD) -f Dockerfile.back .


