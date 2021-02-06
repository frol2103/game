# Game

simple online game

```bash
export PATH_TO_GAME=~/projects/game

export UID

alias dcg="docker-compose -f $PATH_TO_GAME/dev-env/docker-compose.yml"

alias dbGame="docker-compose -f $PATH_TO_GAME/dev-env/docker-compose.yml exec db mysql -h localhost -uuser -ppass db"

```

generate api
```bash
dcg run --rm back "; project generated; openapiGenerate"; dcg run --rm back "; project generateAngularClient; openapiGenerate";
```

generate tables
```bash
dcg run --rm back "genTables"
```
