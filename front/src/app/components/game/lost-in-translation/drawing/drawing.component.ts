import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';

@Component({
  selector: 'lit-drawing',
  templateUrl: './drawing.component.html',
  styleUrls: ['./drawing.component.css']
})
export class DrawingComponent implements OnInit, AfterViewInit {

  constructor() { }

  ngOnInit(): void {
  }

  saveCanvasAsBlob() : Promise<Blob> {
    let canvas = <HTMLCanvasElement> document.getElementById('drawing-canvas')!
    return new Promise<Blob>((resolve, reject) => canvas.toBlob(b => b ? resolve(b!) : reject()))
  }

  ngAfterViewInit(): void {

  }
}
