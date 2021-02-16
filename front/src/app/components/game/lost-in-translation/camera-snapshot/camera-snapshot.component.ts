import {Component, ElementRef, HostListener, OnInit, ViewChild} from '@angular/core';
import {CommonModule} from '@angular/common';

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

    public captures: Array<any>;
    stream: MediaStream

    inputWidth: number = 0
    inputHeight: number = 0
    inputMin: number = 0

    videoWidth: number = 0
    videoOffsetLeft: number = 0
    videoOffsetTop: number = 0
    videoHeight: number = 0

    viewSize: number = 0
    inputToViewRatio: number = 0
    error: boolean = false


    public constructor() {
        this.captures = [];
    }

    public ngOnInit() {
    }

    @HostListener('window:resize', ['$event'])
    onResize(event) {
        this.setVideoPreviewSize()
    }

    private setVideoPreviewSize() {
        let inputRatio = this.inputWidth / this.inputHeight

        console.log("input ratio : "+inputRatio+" ("+this.inputWidth+"x"+this.inputHeight+")")

        let video = this.video.nativeElement;
        let videoView = video.parentElement;
        let videoViewContainer = videoView.parentElement;

        let containerWidth = videoViewContainer.getBoundingClientRect().width

        this.viewSize = containerWidth
        this.inputToViewRatio = this.viewSize / this.inputMin

        if (inputRatio > 1) {
            this.videoHeight = this.viewSize
            this.videoWidth = this.viewSize * inputRatio
            this.videoOffsetLeft = (this.viewSize - this.videoWidth)/2
            this.videoOffsetTop = 0
        } else {
            this.videoHeight = this.viewSize / inputRatio
            this.videoWidth = this.viewSize
            this.videoOffsetLeft = 0
            this.videoOffsetTop = (this.viewSize - this.videoHeight)/2
        }

    }

    public ngAfterViewInit() {
        if (navigator.mediaDevices && navigator.mediaDevices.getUserMedia) {
            navigator.mediaDevices.getUserMedia({video: {facingMode: "environment"}})
                .then((stream: MediaStream) => {
                    console.log("received user approval to use video input")
                    this.stream = stream.clone()
                    let videoTrack = this.stream.getVideoTracks()[0];
                    let videoSettings = videoTrack.getSettings();
                    this.inputWidth = videoSettings.width
                    this.inputHeight = videoSettings.height
                    this.inputMin = Math.min(this.inputWidth, this.inputHeight)
                    let constraints = videoTrack.getConstraints()
                    constraints.advanced
                    videoTrack.applyConstraints(constraints)
                    console.log("stream with reoslution " + videoSettings.width + "x" + videoSettings.height)
                    this.video.nativeElement.srcObject = stream
                    this.video.nativeElement.play()

                    this.setVideoPreviewSize()
                })
                .catch(error => {
                    console.log('Could not find video input : ' + error)
                    this.error = true
                })
        }
    }

    captureAsBlob(): Promise<Blob> {
        let canvas = this.canvas.nativeElement;
        canvas.getContext("2d")
            .drawImage(this.video.nativeElement,
                (this.inputMin - this.inputWidth)/2,
                (this.inputMin - this.inputHeight)/2,
                this.inputWidth,
                this.inputHeight);
        return new Promise<Blob>((resolve, reject) => canvas.toBlob((b: Blob) => b ? resolve(b!) : reject()))
    }

}
