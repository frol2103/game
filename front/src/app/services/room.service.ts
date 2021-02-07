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


  private gameUpdated(game: Game | null) : Game | null {
    if(game) {
      this.refreshActive = true;
      this.ready = true;
      console.log("The game state will now be refreshed every second, current game is game : "+JSON.stringify(game))
    }
    this.game = game;
    return game
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

  goToLatestGame() {
    return this.getAllGames()
        .then(games => {
          if(games && games.length && games[games.length-1].uuid) {
            let gameObservable = this.backendGameService.getGame(games[games.length-1].uuid!);
            return gameObservable.toPromise()
          } else {
            return Promise.resolve(null)
          }
        })
        .then(game => this.gameUpdated(game))
  }

  getAllGames() : Promise<Array<GameDescription>> {
    return this.backendGameService.getAllGames()
        .toPromise();
  }
}

class NewGame implements GameDescription {
  gameType?: GameDescription.GameTypeEnum
}



