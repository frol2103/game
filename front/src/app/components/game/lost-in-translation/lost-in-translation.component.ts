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
import {LitService} from "../../../services/lit.service";
import {GameDescription, LostInTranslationGame} from "../../../../generated/api";
import {DrawingComponent} from "./drawing/drawing.component";
import {CameraSnapshotComponent} from "./camera-snapshot/camera-snapshot.component";
import {LoginService} from "../../../services/login.service";
import {ImageUploadComponent} from "./image-upload/image-upload.component";



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
  @ViewChild('litupload') uploader: ImageUploadComponent

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
    let previoususerInputChoice = this.userService.getUserPref('lostInTranslationInputType')
    if(previoususerInputChoice) {
      this.inputType = previoususerInputChoice
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

  setInputType(newType : LostInTranslationInputType) {
    this.inputType = newType
    this.userService.storeUserPref('lostInTranslationInputType', this.inputType)
  }

  send() {
    this.getCurrentDrawingProvider().captureDrawingAsBlob().then(blob => this.lostInTranslationService.sendDrawing(blob!))
  }

  isGameFinished() {
      return this.lostInTranslationService.game?.game?.description?.status == GameDescription.StatusEnum.Finished
  }

  isInputCameraAvailable() {
    return this.deviceHasCamera
  }

  private getCurrentDrawingProvider() {
    if(this.inputType == LostInTranslationInputType.Camera) {
      return this.camera
    } else if(this.inputType == LostInTranslationInputType.Upload) {
      return this.uploader
    } else {
      return this.childrenDrawings.last
    }
  }

  inputTypes() : Array<LostInTranslationInputType> {
    let types = []
    types.push(LostInTranslationInputType.Draw)
    if(this.isInputCameraAvailable()) {
      types.push(LostInTranslationInputType.Camera)
    }
    types.push(LostInTranslationInputType.Upload)
    return types
  }
}

export interface LitImageProvider {
  captureDrawingAsBlob(): Promise<Blob>
  confirmDrawingSent() : void
}

enum LostInTranslationInputType {
  Draw = 'Draw',
  Camera = 'Camera',
  Upload = 'Upload'
}
