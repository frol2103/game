import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';

@Component({
  selector: 'camera-snapshot',
  templateUrl: './camera-snapshot.component.html',
  styleUrls: ['./camera-snapshot.component.css']
})
export class CameraSnapshotComponent implements OnInit {

  @ViewChild("video")
  public video: ElementRef<HTMLVideoElement>;

  @ViewChild("canvas")
  public canvas: ElementRef<HTMLCanvasElement>;

  @ViewChild("videofull")
  public videofull: ElementRef<HTMLVideoElement>;

  public captures: Array<any>;
  stream : MediaStream
  inputWidth: number = 0
  inputHeight: number = 0


  public constructor() {
    this.captures = [];
  }

  public ngOnInit() { }

  public ngAfterViewInit() {
    if(navigator.mediaDevices && navigator.mediaDevices.getUserMedia) {
      navigator.mediaDevices.getUserMedia({ video: true })
          .then((stream : MediaStream) => {
            console.log("received user approval to use video input")
            this.stream = stream.clone()
            let videoSettings = this.stream.getVideoTracks()[0].getSettings();
            this.inputWidth = videoSettings.width
            this.inputHeight = videoSettings.height
            console.log("stream with reoslution "+videoSettings.width+"x"+videoSettings.height)
            this.video.nativeElement.srcObject = stream
            this.video.nativeElement.play()
      });
    }
  }

  captureAsBlob() : Promise<Blob> {
    let canvas = this.canvas.nativeElement;
    canvas.getContext("2d")
        .drawImage(this.video.nativeElement, 0, 0, this.inputWidth, this.inputHeight);
    //todo crop as needed
    return new Promise<Blob>((resolve, reject) => canvas.toBlob((b : Blob) => b ? resolve(b!) : reject()))
  }
}
