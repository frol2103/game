# Game

simple online game

```bash
export PATH_TO_GAME=~/projects/game

export UID

alias dcg="docker-compose -f $PATH_TO_GAME/dev-env/docker-compose.yml"

alias dbGame="docker-compose -f $PATH_TO_GAME/dev-env/docker-compose.yml exec db mysql -h localhost -uuser -ppass db"

```

On windows (you can add this to your powershell `$profile` file): 
```powershell 
Function dcg { $env:GAME_SBT_CACHE_VOLUME = 'sbt_cache'; docker-compose -f C:\Users\coren\IdeaProjects\game\dev-env\docker-compose.yml $args}
```

generate api
```bash
dcg run --rm back "; project generated; openapiGenerate"; dcg run --rm back "; project generateAngularClient; openapiGenerate";
```

generate tables
```bash
dcg run --rm back "genTables"
```

