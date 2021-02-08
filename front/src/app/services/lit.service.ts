import { Injectable } from '@angular/core';
import {finalize} from "rxjs/operators";
import {Game, GameDescription, GameService, LostInTranslationGame, LostInTranslationService} from "../../generated/api";
import {Observable, timer} from "rxjs";
import {RoomService} from "./room.service";


@Injectable({
  providedIn: 'root'
})
export class LitService {
  public game : LostInTranslationGame | null = null
  private refreshActive : boolean = false


  public constructor(public roomService : RoomService, private backendService : LostInTranslationService) {

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
          .then(game => this.game = game)
    }
  }

}




