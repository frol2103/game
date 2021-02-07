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

info build front
cd $DIR/..
echo "n" | docker-compose -f dev-env/docker-compose.yml run --rm --entrypoint npm front --silent install
docker-compose -f dev-env/docker-compose.yml run --rm front ng build --prod

rm -rf tmp_build || echo ""
mkdir tmp_build

mv front/dist/ tmp_build/front
cp build/Dockerfile.front tmp_build
cp -r build/resources tmp_build

cd $DIR/../tmp_build
docker build -t gamefront:latest -f Dockerfile.front .


