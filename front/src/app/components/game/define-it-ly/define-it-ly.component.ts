import {Component, OnDestroy, OnInit} from '@angular/core';
import {
  DefineItLyGame,
  DefineItLyResponse,
  DefineItLyRound,
  DefineitlyService,
  GameDescription
} from "../../../../generated/api";
import {RoomService} from "../../../services/room.service";
import {pipe, Subscription, timer} from "rxjs";
import {filter, flatMap, mergeMap} from "rxjs/operators";
import {LoginService} from "../../../services/login.service";
import {StringWrapperImpl} from "../../../model/StringWrapperImpl";
import StatusEnum = GameDescription.StatusEnum;


@Component({
  selector: 'define-it-ly',
  templateUrl: './define-it-ly.component.html',
  styleUrls: ['./define-it-ly.component.css']
})
export class DefineItLyComponent implements OnInit, OnDestroy {

  constructor(
      private dilService: DefineitlyService,
      private roomService: RoomService,
      private userService: LoginService,
  ) { }

  game : DefineItLyGame
  gameWithHistory: DefineItLyGame

  currentRound: DefineItLyRound
  questionRound: DefineItLyRound
  refresh: boolean = true
  refreshSub : Subscription
  input : string =""
  showHistory: boolean = true

  ngOnInit(): void {
    this.refreshSub = timer(0, 2000).pipe(
        filter(v => this.refresh),
        mergeMap(v => this.dilService.getGame(this.roomService.game.description.uuid))
    ).subscribe(v=>this.updateGame(v))
  }


  ngOnDestroy(): void{
    this.refreshSub.unsubscribe();
  }

  isMyRound(r) {
    return r.quedtionByUser.uuid === this.userService.user.uuid
  }

  shouldEnterResponse(){
    const r = this.currentRound.status == DefineItLyRound.StatusEnum.WaitResponses
        && this.currentRound.responses.find(v => v.responseByUser.user.uuid == this.userService.user.uuid) == undefined

    return r;
  }

  sendQuestion(){
    this.dilService.addQuestion(this.game.game.description.uuid, new StringWrapperImpl(this.input)).toPromise()
        .then(v => {
          this.updateGame(v)
          this.input = undefined;
        })
  }

  sendResponse(){
    this.dilService.addResponse(this.game.game.description.uuid,this.currentRound.roundId, new StringWrapperImpl(this.input)).toPromise()
        .then(v => {
          this.updateGame(v)
          this.input = undefined;
        })
  }
  chose(r:DefineItLyResponse){
    return this.dilService.choseResponse(this.game.game.description.uuid, this.currentRound.roundId, new StringWrapperImpl(r.uuid)).toPromise()
        .then(v => {
          this.updateGame(v)
          this.input = undefined;
        })
  }

  showResponses(){
    return this.currentRound.status === DefineItLyRound.StatusEnum.WaitChoices;
  }

  waitResponses(){
    return this.currentRound.status === DefineItLyRound.StatusEnum.WaitResponses;
  }

  allowChoice(){
    return this.currentRound.quedtionByUser.uuid !== this.userService.user.uuid;
  }
  enableChoice(){
    return this.allowChoice() && !this.currentChoice();

  }

  currentChoice() : DefineItLyResponse | undefined{
    return this.currentRound.responses.find(r => r.chosenBy.find(v => v.uuid === this.userService.user.uuid))
  }
  myResponse() : DefineItLyResponse | undefined{
    return this.currentRound.responses.find(r => r.responseByUser.user.uuid === this.userService.user.uuid)
  }

  currentRoundFinished(){
    return this.currentRound!= undefined && this.currentRound.status === DefineItLyRound.StatusEnum.Finished;
  }

  updateGame(g:DefineItLyGame){
    this.game = g
    this.currentRound = this.game.rounds.filter(r => r.status !== DefineItLyRound.StatusEnum.WaitQuestion).slice(-1)[0]
    this.questionRound = this.game.rounds.filter(r => r.status === DefineItLyRound.StatusEnum.WaitQuestion
        && this.isMyRound(r)).slice(-1)[0]

    const lastFinishedRoundInHistory =(this.gameWithHistory)?this.gameWithHistory.rounds.filter(r => r.status !== DefineItLyRound.StatusEnum.Finished).slice(-1)[0]:undefined
    const lastFinishedRoundInGame =this.game.rounds.filter(r => r.status !== DefineItLyRound.StatusEnum.Finished).slice(-1)[0]
    if(lastFinishedRoundInGame && (!lastFinishedRoundInHistory || lastFinishedRoundInHistory.roundId !== lastFinishedRoundInGame.roundId)){
      this.dilService.getGame(this.roomService.game.description.uuid,true).toPromise().then(v => this.gameWithHistory = v)
    }
  }

  finishedRounds(g: DefineItLyGame) {
    return g.rounds.filter(v => v.status === DefineItLyRound.StatusEnum.Finished)
  }
}
