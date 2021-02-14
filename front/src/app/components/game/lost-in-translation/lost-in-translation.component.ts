import {
  AfterViewInit,
  Component, ComponentRef,
  ElementRef,
  OnDestroy,
  OnInit,
  QueryList,
  ViewChild,
  ViewChildren
} from '@angular/core';
import {RoomService} from "../../../services/room.service";
import {LitService} from "../../../services/lit.service";
import {GameDescription, LostInTranslationGame} from "../../../../generated/api";
import {DrawingComponent} from "./drawing/drawing.component";
import {CameraSnapshotComponent} from "./camera-snapshot/camera-snapshot.component";



@Component({
  selector: 'lost-in-translation',
  templateUrl: './lost-in-translation.component.html',
  styleUrls: ['./lost-in-translation.component.css']
})
export class LostInTranslationComponent implements OnInit, AfterViewInit, OnDestroy {
  constructor(public lostInTranslationService: LitService) { }
  text: string = '';

  inputType : LostInTranslationInputType = LostInTranslationInputType.Draw

  @ViewChildren(DrawingComponent) childrenDrawings: QueryList<DrawingComponent> = new QueryList<DrawingComponent>()

  @ViewChild('litcamera') camera: CameraSnapshotComponent

  drawing: DrawingComponent | null = null
  deviceHasCamera: boolean = false
  deviceHasCameraReady: boolean = false


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
    navigator.mediaDevices.getUserMedia({ video: {facingMode: "environment"} })
        .then(media => this.deviceHasCamera = media != null)
        .finally(() => this.deviceHasCameraReady = true)
  }

  ngOnDestroy(): void {
    this.lostInTranslationService.stop()
  }

  sendText() {
    this.lostInTranslationService.sendText(this.text)
  }

  switchInputType() {
    if(this.isInputDraw()) {
      this.inputType = LostInTranslationInputType.Camera
    } else {
      this.inputType = LostInTranslationInputType.Draw
    }
  }

  sendCameraCapture() {
    this.camera.captureAsBlob().then(blob => this.lostInTranslationService.sendDrawing(blob!))
  }

  sendDrawing() {
    this.drawing!.saveCanvasAsBlob().then(blob => this.lostInTranslationService.sendDrawing(blob!))
  }

  isGameFinished() {
      return this.lostInTranslationService.game?.game?.description?.status == GameDescription.StatusEnum.Finished
  }

  isInputDraw() {
    return this.inputType == LostInTranslationInputType.Draw
  }

  isInputCameraAvailable() {
    return this.deviceHasCamera
  }

  isInputCamera() {
    return this.inputType == LostInTranslationInputType.Camera
  }
}

enum LostInTranslationInputType {
  Draw,
  Camera
}
