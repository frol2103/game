import { Injectable } from '@angular/core';
import {finalize} from "rxjs/operators";
import {
  Game,
  GameDescription,
  GameService,
  LostInTranslationGame,
  LostInTranslationRound,
  LostInTranslationService
} from "../../generated/api";
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
    this.game = game
    if(! game.rounds || !game.rounds.length) {
      this.isWaitingForOtherPlayers = true
      this.isFirstRound = false
    } else {
      this.isWaitingForOtherPlayers = false
      let currentRound = this.getCurrentRound();
      this.isFirstRound = !currentRound.drawing && !currentRound.text
      this.isTextRound = this.isFirstRound || currentRound.drawing != null
    }
    return game
  }

  getCurrentRound() : LostInTranslationRound {
    return this.game!.rounds![0]!
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




