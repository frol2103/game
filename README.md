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


## Api changes ideas
- separate the notion of room from the games to allow keeping your friends group together when playing another game, example : 
    - User A creates a room (currently creates a game) and invites his friends B and C 
    - User A starts a game (currently start te previously created game) of type X 
    - After the game is finished, user A should be able to either play the same game again with B and C or choose another game type Y for a new game
- polling games for changes in user list every second might become too much for the server if multiple games are in progress : it will scale with the total amount of active users browser tabs. Maybe look into a publish/subscribe mechanism where the frontend can subscribe to updates for a game and receives the game data through a websocket only if there is a game event (user joined a room or a round was played...)