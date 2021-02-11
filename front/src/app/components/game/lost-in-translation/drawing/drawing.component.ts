import {AfterViewInit, Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {fromEvent, Observable, throwError} from "rxjs";
import {pairwise, switchMap, takeUntil} from "rxjs/operators";


@Component({
  selector: 'lit-drawing',
  templateUrl: './drawing.component.html',
  styleUrls: ['./drawing.component.css']
})
export class DrawingComponent implements OnInit, AfterViewInit {
  sketchpad: any
  @ViewChild('drawingcanvas') canvas: ElementRef | undefined
  @Input() public size = 600;
  ctx : CanvasRenderingContext2D | undefined

  constructor() { }

  ngOnInit(): void {
  }

  saveCanvasAsBlob() : Promise<Blob> {
    return new Promise<Blob>((resolve, reject) => this.canvas!.nativeElement.toBlob((b : Blob) => b ? resolve(b!) : reject()))
  }

  ngAfterViewInit(): void {
    this.initContext(this.canvas!.nativeElement)
    this.captureStrokes('mousedown', 'mousemove', 'mouseup', 'mouseleave')
    // this.captureStrokes('touchstart', 'touchmove', 'touchend', 'touchcancel')
  }

  private fromEvent(eventType : string) : Observable<Event> {
    return fromEvent(this.canvas!.nativeElement, eventType)
  }

  private captureStrokes(startEvent: string, moveEvent: string, stopEvent: string, cancelEvent: string) {
    this.fromEvent(startEvent)
        .pipe(
            switchMap(event => this.fromEvent(moveEvent).pipe(takeUntil(this.fromEvent(stopEvent)), takeUntil(this.fromEvent(cancelEvent)), pairwise()))
        )
        .subscribe(stroke=> this.drawLine(stroke[0], stroke[1]))
  }

  private initContext(canvas: HTMLCanvasElement) {
    canvas.width = this.size
    canvas.height = this.size
    this.ctx = canvas.getContext('2d')!
    this.ctx.lineCap = 'round'
    this.ctx.lineWidth = 3
    this.ctx.strokeStyle = '#000000'

  }

  private drawLine(start: Event, end: Event) {
    let rect = this.canvas!.nativeElement.getBoundingClientRect()

    console.log('Draw for event '+(<MouseEvent> start).clientX+"/"+(<MouseEvent> start).clientY)
    let startX : number = (start instanceof MouseEvent ? (<MouseEvent> start).clientX : (<TouchEvent> start).touches!.item(0)!.clientX) - rect.left
    let startY : number = (start instanceof MouseEvent ? (<MouseEvent> start).clientY : (<TouchEvent> start).touches!.item(0)!.clientY) - rect.top
    let endX : number = (start instanceof MouseEvent ? (<MouseEvent> end).clientX : (<TouchEvent> end).touches!.item(0)!.clientX) - rect.left
    let endY : number = (start instanceof MouseEvent ? (<MouseEvent> end).clientY : (<TouchEvent> end).touches!.item(0)!.clientY) - rect.top

    console.log('draw line from '+startX +' - '+ startY+" bounded by "+rect.left+" - "+rect.top)


    this.ctx!.beginPath()
    this.ctx!.moveTo(startX , startY)
    this.ctx!.lineTo(endX, endY)
    this.ctx!.stroke()
  }
}
