import { Component, OnInit } from '@angular/core';
import {LitImageProvider} from "../lost-in-translation.component";



@Component({
  selector: 'lit-image-upload',
  templateUrl: './image-upload.component.html',
  styleUrls: ['./image-upload.component.css']
})
export class ImageUploadComponent implements OnInit, LitImageProvider {
  file : File
  constructor() { }

  ngOnInit(): void {
  }

  onFileChange(event) {
    this.file = event.target.files[0]
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

  confirmDrawingSent(): void {
  }
}
