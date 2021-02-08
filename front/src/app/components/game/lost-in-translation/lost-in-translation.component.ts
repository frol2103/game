import {Component, OnDestroy, OnInit} from '@angular/core';
import {RoomService} from "../../../services/room.service";
import {LitService} from "../../../services/lit.service";
import {LostInTranslationGame} from "../../../../generated/api";

@Component({
  selector: 'lost-in-translation',
  templateUrl: './lost-in-translation.component.html',
  styleUrls: ['./lost-in-translation.component.css']
})
export class LostInTranslationComponent implements OnInit, OnDestroy {
  constructor(public lostInTranslationService: LitService) { }
  text: string = '';

  ngOnInit(): void {
    this.lostInTranslationService.init()
  }

  ngOnDestroy(): void {
    this.lostInTranslationService.stop()
  }

  sendText() {
    this.lostInTranslationService.sendText(this.text)
  }

}
