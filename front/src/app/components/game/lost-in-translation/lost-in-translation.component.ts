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
import {LoginService} from "../../../services/login.service";



@Component({
  selector: 'lost-in-translation',
  templateUrl: './lost-in-translation.component.html',
  styleUrls: ['./lost-in-translation.component.css']
})
export class LostInTranslationComponent implements OnInit, AfterViewInit, OnDestroy {
  constructor(public lostInTranslationService: LitService, public userService : LoginService) { }
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
    if(this.userService.userPrefs['lostInTranslationInputType']) {
      this.inputType = this.userService.userPrefs['lostInTranslationInputType']
    }


    this.lostInTranslationService.init()
    this.text = ''

    this.deviceHasCamera = navigator.mediaDevices != null
    this.deviceHasCameraReady = true
  }

  ngOnDestroy(): void {
    this.lostInTranslationService.stop()
  }

  sendText() {
    this.lostInTranslationService.sendText(this.text)
    this.text = ''
  }

  switchInputType() {
    if(this.isInputDraw()) {
      this.inputType = LostInTranslationInputType.Camera
    } else {
      this.inputType = LostInTranslationInputType.Draw
    }
    this.userService.userPrefs['lostInTranslationInputType'] = this.inputType
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

  onFileChange(event) {
    this.lostInTranslationService.backendService.addDrawingRound(this.lostInTranslationService.game.game.description.uuid,
        this.lostInTranslationService.getStoryToPlay().storyId,
        event.target.files[0])
      .toPromise()
        .then(console.log)
  }
}

enum LostInTranslationInputType {
  Draw,
  Camera
}
