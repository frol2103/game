import { Injectable } from '@angular/core';
import {finalize} from "rxjs/operators";
import {
  Game,
  GameDescription,
  GameService,
  LostInTranslationGame,
  LostInTranslationRound,
  LostInTranslationService, 
  LostInTranslationStory,
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

  public constructor(public roomService : RoomService, public backendService : LostInTranslationService, private login : LoginService) {

  }

  public init() {
    this.stop()
    this.refreshActive = true
    timer(0, 2000).subscribe(time => this.fetchGameInfo())
  }

  imageUrl(drawing: string) {
    return '/api/file/'+drawing
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
      this.isFirstRound = this.checkIfFirstRound(currentRound)
    } else {
      this.isWaitingForOtherPlayers = true
      this.isFirstRound = false
    }

    if(this.roomService.game?.description?.status == GameDescription.StatusEnum.Finished) {
      this.refreshActive = false
    }

    return game
  }

  private checkIfFirstRound(currentRound: LostInTranslationRound) {
    return currentRound.roundUser?.uuid == this.findStory(currentRound).originalUser?.uuid;
  }


  private priority(round: LostInTranslationRound) {
    return - this.findStory(round)!.rounds!.filter(r => r.submissionDate).length
  }
  private storyPiority(story: LostInTranslationStory) {
    return - story.rounds!.length
  }

  private findStory(round: LostInTranslationRound) {
    return this.game!.stories!.find(s => s.storyId == round.storyId);
  }

  getStoryToPlay() : LostInTranslationStory | undefined {
    const candidates =  this.game.stories.filter(s => {
      const lastRound = s.rounds.slice(-1)[0]
      return lastRound.roundUser.uuid === this.login.user.uuid && !lastRound.submissionDate
    }).sort((s1,s2) => this.storyPiority(s2) - this.storyPiority(s1))
    return (candidates.length > 0)?candidates[0]:undefined;

  }

  getCurrentRound() : LostInTranslationRound | null {
    const stoplay = this.getStoryToPlay();
    return (stoplay)?stoplay.rounds.slice(-1)[0]:undefined;
  }

  getPreviousRound() : LostInTranslationRound {
    const story = this.getStoryToPlay();
    return (story.rounds.length>1)?story.rounds.slice(-2)[0]:undefined;
  }

  sendText(text: string) : Promise<LostInTranslationGame> {
    return this.backendService.addTextRound(this.game!.game!.description!.uuid!, this.getCurrentRound()?.storyId!,'"'+text+'"')
        .toPromise()
        .then(g => this.updateGame(g))
  }

  sendDrawing(drawing: Blob) : Promise<LostInTranslationGame> {
    return this.backendService.addDrawingRound(this.game!.game!.description!.uuid!, this.getCurrentRound()?.storyId!, drawing)
        .toPromise()
        .then(g => this.updateGame(g))
  }
}




