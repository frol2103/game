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
        .pipe(finalize(() => this.ready=true))
        .subscribe(game => this.gameCreated(game), error => this.game = null)
    return observable
  }


  private gameCreated(game: Game) {
    this.refreshActive = true;
    this.game = game;
  }

  public fetchGameInfo() {
    if(this.refreshActive && this.game?.description?.uuid) {
      this.backendGameService.getGame(this.game?.description?.uuid)
          .pipe(finalize(() => this.ready=true))
          .subscribe(game => this.game = game, error => this.game = null)
    }
  }

}

class NewGame implements GameDescription {
  gameType?: GameDescription.GameTypeEnum
}



