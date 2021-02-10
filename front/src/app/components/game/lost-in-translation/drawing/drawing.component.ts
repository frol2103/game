import {Component, OnInit, ViewChild} from '@angular/core';

@Component({
  selector: 'lit-drawing',
  templateUrl: './drawing.component.html',
  styleUrls: ['./drawing.component.css']
})
export class DrawingComponent implements OnInit {

  @ViewChild('drawingcanvas') drawing: HTMLCanvasElement | null = null;


  constructor() { }

  ngOnInit(): void {
  }

  saveCanvasAsBlob() : Promise<Blob> {
    return drawing.blob()
  }
}
