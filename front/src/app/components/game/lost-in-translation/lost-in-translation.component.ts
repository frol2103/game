import {AfterViewInit, Component, OnDestroy, OnInit, QueryList, ViewChild, ViewChildren} from '@angular/core';
import {RoomService} from "../../../services/room.service";
import {LitService} from "../../../services/lit.service";
import {GameDescription, LostInTranslationGame} from "../../../../generated/api";
import {DrawingComponent} from "./drawing/drawing.component";

@Component({
  selector: 'lost-in-translation',
  templateUrl: './lost-in-translation.component.html',
  styleUrls: ['./lost-in-translation.component.css']
})
export class LostInTranslationComponent implements OnInit, AfterViewInit, OnDestroy {
  constructor(public lostInTranslationService: LitService) { }
  text: string = '';

  @ViewChildren(DrawingComponent) childrenDrawings: QueryList<DrawingComponent> = new QueryList<DrawingComponent>()
  drawing: DrawingComponent | null = null

  ngAfterViewInit(): void {
    this.childrenDrawings.changes.subscribe((comps: QueryList<DrawingComponent>) =>
    {
      console.log("found child component")
      this.drawing = comps.last
      console.log("found child component --> "+this.drawing)
    });
  }

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
