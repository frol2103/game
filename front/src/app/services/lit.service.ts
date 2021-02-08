import { Injectable } from '@angular/core';
import {finalize} from "rxjs/operators";
import {Game, GameDescription, GameService, LostInTranslationGame, LostInTranslationService} from "../../generated/api";
import {Observable, timer} from "rxjs";
import {RoomService} from "./room.service";
import {LoginService} from "./login.service";


@Injectable({
  providedIn: 'root'
})
export class LitService {
  public game : LostInTranslationGame | null = null
  private refreshActive : boolean = false
  isTextRound : boolean = true
  isFirstRound : boolean = true
  isWaitingForOtherPlayers : boolean = false


  public constructor(public roomService : RoomService, private backendService : LostInTranslationService, private login : LoginService) {

  }

  public init() {
    this.stop()
    this.refreshActive = true
    timer(0, 1000).subscribe(time => this.fetchGameInfo())
  }

  public stop() {
    this.refreshActive = false
    this.game = null
  }

  public fetchGameInfo() {
    let uuid = this.roomService.game?.description?.uuid!;
    if(this.refreshActive && uuid) {
      this.backendService.getGame(uuid)
          .toPromise()
          .then(game => this.updateGame(game))
    }
  }

  private updateGame(game: LostInTranslationGame) {
    this.isFirstRound = game.rounds?.length == 1
    this.isTextRound = game.rounds!.length! % 2 == 1
    this.isWaitingForOtherPlayers = false
    //todo: not enough info in current rounds list to know what the round is

    return this.game = game;
  }

  sendText(text: string) : Promise<LostInTranslationGame> {
    return this.backendService.addTextRound(this.game!.game!.description!.uuid!, '"'+text+'"')
        .toPromise()
  }

  sendDrawing(drawing: Blob) : Promise<LostInTranslationGame> {
    return this.backendService.addDrawingRound(this.game!.game!.description!.uuid!, drawing)
        .toPromise()
  }
}




