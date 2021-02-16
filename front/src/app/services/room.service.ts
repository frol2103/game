import { Injectable } from '@angular/core';
import {finalize} from "rxjs/operators";
import {Game, GameDescription, GameService} from "../../generated/api";
import {Observable, timer} from "rxjs";
import GameTypeEnum = GameDescription.GameTypeEnum;


@Injectable({
  providedIn: 'root'
})
export class RoomService {
  public ready : boolean = false
  public game : Game | null = null
  private refreshActive : boolean = false

  public constructor(private backendGameService : GameService) {
    timer(0, 1500).subscribe(time => this.fetchGameInfo())
  }

  public createGame(gameType:GameTypeEnum) : Promise<Game> {
    console.log("Creating new game")
    let gamePromise = this.backendGameService.createAGame(new NewGame(gameType))
        .toPromise();
    gamePromise.then(game => this.gameUpdated(game))
    return gamePromise
  }


  private gameUpdated(game: Game | null) : Game | null {
    if(game) {
      this.refreshActive = game.description?.status != GameDescription.StatusEnum.InPlay;
      this.ready = true;
      console.log("The game state will now be refreshed every second, current game is game : "+JSON.stringify(game))
    }
    this.game = game;
    return game
  }

  public startGame() {
    if(this.refreshActive && this.game?.description?.uuid) {
      this.backendGameService.startGame(this.game?.description?.uuid)
          .toPromise()
          .then(game => this.gameUpdated(game))
    }
  }

  destroy() {
    this.refreshActive = false
  }

  public fetchGameInfo() {
    let uuid = this.game?.description?.uuid;
    if(this.refreshActive && uuid) {
      this.gotoGame(uuid);
    }
  }

  private gotoGame(uuid: string) : Promise<Game> {
    return this.backendGameService.getGame(uuid)
        .toPromise()
        .then(game => this.game = game)
        .finally(() => this.ready = true)
  }

  join(gameUuid: string) : Promise<Game> {
    return this.backendGameService.joinGame(gameUuid)
        .toPromise()
        .then(game => {
          console.log("Joined game : "+JSON.stringify(game))
          this.gameUpdated(game)
          return game
        })
  }

  setCurrent(gameUuid: string) {
    if(this.game?.description?.uuid == gameUuid) {
      return Promise.resolve(this.game)
    } else {
      return this.gotoGame(gameUuid)
    }
  }

  getAllGames() : Promise<Array<GameDescription>> {
    return this.backendGameService.getAllGames()
        .toPromise();
  }
}

class NewGame implements GameDescription {
  gameType?: GameDescription.GameTypeEnum

  constructor(gameType: GameDescription.GameTypeEnum) {
    this.gameType = gameType
  }
}



