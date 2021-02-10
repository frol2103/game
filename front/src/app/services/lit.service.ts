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
    let uuid = this.roomService.game?.description?.uuid;
    if(uuid && this.refreshActive) {
      this.backendService.getGame(uuid)
          .toPromise()
          .then(game => this.updateGame(game))
    }
  }

  private updateGame(game: LostInTranslationGame) {
    this.game = game
    let currentRound = this.getCurrentRound()
    if(currentRound) {
      this.isWaitingForOtherPlayers = false
      this.isTextRound = currentRound.roundType == LostInTranslationRound.RoundTypeEnum.Text
      this.isFirstRound = this.isTextRound && !currentRound.drawing
    } else {
      this.isWaitingForOtherPlayers = true
      this.isFirstRound = false
    }

    if(this.roomService.game?.description?.status == GameDescription.StatusEnum.Finished) {
      this.refreshActive = false
    }

    return game
  }

  private getRoundsToPlay() {
    let stories = this.game?.stories ? this.game?.stories : []
    return stories.flatMap(s => s.rounds!.filter(r => r.roundUser?.uuid == this.login.user?.uuid && !r.submissionDate));
  }

  getCurrentRound() : LostInTranslationRound | null {
    let roundsToPlay = this.getRoundsToPlay();
    return roundsToPlay && roundsToPlay.length ? roundsToPlay[0] : null
  }

  sendText(text: string) : Promise<LostInTranslationGame> {
    return this.backendService.addTextRound(this.game!.game!.description!.uuid!, this.getCurrentRound()?.storyId!,'"'+text+'"')
        .toPromise()
  }

  sendDrawing(drawing: Blob) : Promise<LostInTranslationGame> {
    return this.backendService.addDrawingRound(this.game!.game!.description!.uuid!, this.getCurrentRound()?.storyId!, drawing)
        .toPromise()
  }
}




