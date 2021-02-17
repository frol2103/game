import { Component, OnInit } from '@angular/core';
import {LitImageProvider} from "../lost-in-translation.component";
import {ImageCroppedEvent} from "ngx-image-cropper";

const b64toBlob = (b64Data, contentType='', sliceSize=512) => {
  const byteCharacters = atob(b64Data);
  const byteArrays = [];

  for (let offset = 0; offset < byteCharacters.length; offset += sliceSize) {
    const slice = byteCharacters.slice(offset, offset + sliceSize);

    const byteNumbers = new Array(slice.length);
    for (let i = 0; i < slice.length; i++) {
      byteNumbers[i] = slice.charCodeAt(i);
    }

    const byteArray = new Uint8Array(byteNumbers);
    byteArrays.push(byteArray);
  }

  const blob = new Blob(byteArrays, {type: contentType});
  return blob;
}

@Component({
  selector: 'lit-image-upload',
  templateUrl: './image-upload.component.html',
  styleUrls: ['./image-upload.component.css']
})
export class ImageUploadComponent implements OnInit, LitImageProvider {
  file : File
  imageChangedEvent: any = '';
  croppedImage: Blob = null

  constructor() { }

  ngOnInit(): void {
  }

  onFileChange(event) {
    this.file = event.target.files[0]
    this.fileChangeEvent(event)
  }

  isDrawingReady(): boolean {
    return this.croppedImage != null
  }

  captureDrawingAsBlob() : Promise<Blob> {
    return new Promise<Blob>((resolve, reject) => {
      if(this.file) {
        resolve(this.file)
      } else {
        reject('No file selected')
      }
    })
  }



  fileChangeEvent(event: any): void {
    this.imageChangedEvent = event;
  }

  imageCropped(event: ImageCroppedEvent) {
    this.croppedImage = b64toBlob(event.base64.split("base64,")[1])
  }

  imageLoaded(image: HTMLImageElement = undefined) {
    // show cropper
  }
  cropperReady() {
    // cropper ready
  }
  loadImageFailed() {
    // show message
  }

  confirmDrawingSent(): void {
  }
}
