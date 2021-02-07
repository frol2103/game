import { Injectable } from '@angular/core';
import {finalize} from "rxjs/operators";
import {Game, GameDescription, GameService} from "../../generated/api";
import {Observable, timer} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class RoomService {
  public ready : boolean = false
  public game : Game | null = null
  private refreshActive : boolean = false

  public constructor(private backendGameService : GameService) {
    timer(0, 1000).subscribe(time => this.fetchGameInfo())
  }

  public createLostInTranslationGame() : Observable<Game> {
    let gameDescription = new NewGame();
    gameDescription.gameType = GameDescription.GameTypeEnum.LostInTranslation
    let observable = this.backendGameService.createAGame(gameDescription);
    observable
        .subscribe(game => this.gameUpdated(game), error => this.game = null)
    return observable
  }


  private gameUpdated(game: Game) {
    this.refreshActive = true;
    this.ready = true;
    this.game = game;
    console.log("The game state will now be refreshed every second, current game is game : "+JSON.stringify(game))
  }

  public startGame() {
    if(this.refreshActive && this.game?.description?.uuid) {
      this.backendGameService.startGame(this.game?.description?.uuid)
    }
  }

  public fetchGameInfo() {
    if(this.refreshActive && this.game?.description?.uuid) {
      this.backendGameService.getGame(this.game?.description?.uuid)
          .pipe(finalize(() => this.ready=true))
          .subscribe(game => this.game = game, error => this.game = null)
    }
  }

  join(gameUuid: string) {
    let observable = this.backendGameService.joinGame(gameUuid)
    observable
        .subscribe(game => {
          console.log("Joined game : "+JSON.stringify(game))
          this.gameUpdated(game)
        }, error => this.game = null)
    return observable
  }

  refreshCurrentGame() : Promise<Game|null> {
    return this.backendGameService.getAllGames()
        .toPromise()
        .then(games => {
          if(games && games.length && games[0].uuid) {
            console.log("Will go back to active game for user : "+games[0].uuid)
            let gameObservable = this.backendGameService.getGame(games[0].uuid);
            gameObservable.subscribe(game => this.gameUpdated(game))
            return gameObservable.toPromise()
          } else {
            return Promise.resolve(null)
          }
        })
  }
}

class NewGame implements GameDescription {
  gameType?: GameDescription.GameTypeEnum
}



