# Paper(less) games

A web applications with some paper-based games to play with friends.

## Dev environment

#### Tools needed
- Docker 
- Docker-compose 
- An IDE or text editor 

#### Create aliases for the docker-compose 

- On linux :

    ```bash
    export PATH_TO_GAME=~/projects/game
    
    export UID
    
    alias dcg="docker-compose -f $PATH_TO_GAME/dev-env/docker-compose.yml"
    
    alias dbGame="docker-compose -f $PATH_TO_GAME/dev-env/docker-compose.yml exec db mysql -h localhost -uuser -ppass db"
    
    ```

- On windows, use powershell and execute this after replacing `PROJECT_ROOT` with the path where you cloned this repository (or you can add this to your powershell `$profile` file) : 
    ```powershell 
    Function dcg { $env:GAME_SBT_CACHE_VOLUME = 'sbt_cache'; docker-compose -f PROJECT_ROOT\dev-env\docker-compose.yml $args}
    ```

#### Local environment basic commands

- start/restart the local environment : 
    ```bash
    dcg down; dcg up -d --build
    ```
    You can then open the frontend on http://localhost:4200

 - generate sources from the openapi specifications : 
    ```bash
    dcg run --rm back "; project generated; openapiGenerate"; dcg run --rm back "; project generateAngularClient; openapiGenerate";
    ```

- generate tables
    ```bash
    dcg run --rm back "genTables"
    ```

