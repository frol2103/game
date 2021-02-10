import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {RoomService} from "../../../services/room.service";
import {LitService} from "../../../services/lit.service";
import {GameDescription, LostInTranslationGame} from "../../../../generated/api";
import {DrawingComponent} from "./drawing/drawing.component";

@Component({
  selector: 'lost-in-translation',
  templateUrl: './lost-in-translation.component.html',
  styleUrls: ['./lost-in-translation.component.css']
})
export class LostInTranslationComponent implements OnInit, OnDestroy {
  constructor(public lostInTranslationService: LitService) { }
  text: string = '';

  @ViewChild('litdrawing') drawing: DrawingComponent | null = null;

  ngOnInit(): void {
    this.lostInTranslationService.init()
  }

  ngOnDestroy(): void {
    this.lostInTranslationService.stop()
  }

  sendText() {
    this.lostInTranslationService.sendText(this.text)
  }

  sendDrawing() {
    this.drawing!.saveCanvasAsBlob().then(blob => this.lostInTranslationService.sendDrawing(blob!))
  }

  isGameFinished() {
      return this.lostInTranslationService.game?.game?.description?.status == GameDescription.StatusEnum.Finished
  }
}
