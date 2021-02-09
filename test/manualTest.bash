#!/bin/bash


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

function curlGame() {
	curl -b /tmp/gameCookies$1 -c /tmp/gameCookies$1 -H "accept: application/json" -H  "Content-Type: application/json"  "${@:2}" 
}
function curlGameMultipart() {
	curl -b /tmp/gameCookies$1 -c /tmp/gameCookies$1 -H "accept: application/json" -H  "Content-Type: multipart/form-data"  "${@:2}" 
}

function roundFor() {
	curlGame 1  -X GET http://localhost:9000/api/game/LostInTranslation/$GAME_UUID | jq '.stories[].rounds[] | select(.submissionDate == null) | select(.roundUser.name == "'$1'")'
}


info connect
curl -c /tmp/gameCookies1 -D- -X POST "http://localhost:4200/api/login" -H  "accept: application/json" -H  "Content-Type: application/json" -d '"frol"'
curl -c /tmp/gameCookies2 -D- -X POST "http://localhost:4200/api/login" -H  "accept: application/json" -H  "Content-Type: application/json" -d '"ame"'
curl -c /tmp/gameCookies3 -D- -X POST "http://localhost:4200/api/login" -H  "accept: application/json" -H  "Content-Type: application/json" -d '"coco"'

info create game
export GAME_UUID=$(curlGame 1  -X POST -d '{"gameType":"lostInTranslation"}' http://localhost:9000/api/game/create | jq -r '.description.uuid')

info user 1 join, should have no effect
curlGame 1  -X GET http://localhost:9000/api/game/$GAME_UUID/join | jq '.'

info user 2 and 3 join
curlGame 2  -X GET http://localhost:9000/api/game/$GAME_UUID/join | jq '.'
curlGame 3  -X GET http://localhost:9000/api/game/$GAME_UUID/join | jq '.'

info start game
curlGame 1  -X GET http://localhost:9000/api/game/$GAME_UUID/start | jq '.'

info lit game
curlGame 1  -X GET http://localhost:9000/api/game/LostInTranslation/$GAME_UUID | jq '.'

info play initial round frol
curlGame 1  -X POST -d '"init frol"' http://localhost:9000/api/game/LostInTranslation/$GAME_UUID/story/$(roundFor frol | jq -r '.storyId')/text | jq '.'

touch test.png

info play response ame on frol story
curlGameMultipart 2 \
	-X POST \
	-F "file=@$(pwd)/test.png;type=image/png" \
       	http://localhost:9000/api/game/LostInTranslation/$GAME_UUID/story/$(roundFor ame | jq 'select(.originalUser.name == "frol")' | jq -r '.storyId')/drawing \
	| jq '.'

info play initial round ame
curlGame 2  -X POST -d '"init ame"' http://localhost:9000/api/game/LostInTranslation/$GAME_UUID/story/$(roundFor frol | jq -r '.storyId')/text | jq '.'
