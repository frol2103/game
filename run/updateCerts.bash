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


$DIR/dc.bash run --rm letsencrypt \
	--text \
	--non-interactive \
	--no-bootstrap \
	--no-self-upgrade certonly \
	--webroot --webroot-path=/var/www/letsencrypt \
	--renew-by-default \
	-d game.frol.be \
	--agree-tos \
	--email gameplatform@frol.be

$DIR/dc.bash restart front 
