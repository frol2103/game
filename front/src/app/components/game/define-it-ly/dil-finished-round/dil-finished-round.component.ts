import {Component, Input, OnInit} from '@angular/core';
import {DefineItLyGame, DefineItLyResponse, DefineItLyRound} from "../../../../../generated/api";

@Component({
  selector: 'dil-finished-round',
  templateUrl: './dil-finished-round.component.html',
  styleUrls: ['./dil-finished-round.component.css']
})
export class DilFinishedRoundComponent implements OnInit {

  constructor() { }

  @Input()
  round : DefineItLyRound;

  @Input()
  game : DefineItLyGame;

  points(r:DefineItLyResponse) {
    return r.responseByUser.points.map(v => v.amount).reduce((a,b)=>a+b,0)
  }

  ngOnInit(): void {
  }

}
