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

  activeStrokes : Array<DrawStroke> = []
  removedStrokes : Array<DrawStroke> = []
  currentStyle : DrawStyle = new DrawStyle()

  constructor() { }

  ngOnInit(): void {
  }

  saveCanvasAsBlob() : Promise<Blob> {
    return new Promise<Blob>((resolve, reject) => this.canvas!.nativeElement.toBlob((b : Blob) => b ? resolve(b!) : reject()))
  }

  ngAfterViewInit(): void {
    this.initContext(this.canvas!.nativeElement)
    this.captureStrokes('mousedown', 'mousemove', 'mouseup', 'mouseleave')
    this.captureStrokes('touchstart', 'touchmove', 'touchend', 'touchcancel')
  }

  private fromEvent(eventType : string) : Observable<Event> {
    return fromEvent(this.canvas!.nativeElement, eventType)
  }

  private captureStrokes(startEvent: string, moveEvent: string, stopEvent: string, cancelEvent: string) {
    this.fromEvent(startEvent)
        .subscribe(startStrokeEvent => this.activeStrokes.push(new DrawStroke()))
    this.fromEvent(startEvent)
        .pipe(
            switchMap(event => this.fromEvent(moveEvent).pipe(takeUntil(this.fromEvent(stopEvent)), takeUntil(this.fromEvent(cancelEvent)), pairwise()))
        )
        .subscribe(stroke=> this.newLine(new StrokeSegment(this.toCoordinates(stroke[0]), this.toCoordinates(stroke[1]))))
  }

  private initContext(canvas: HTMLCanvasElement) {
    canvas.width = this.size
    canvas.height = this.size
    this.ctx = canvas.getContext('2d')
  }

  clear() {
    this.ctx!.clearRect(0, 0, this.size, this.size)
    this.removedStrokes = this.activeStrokes.reverse()
    this.activeStrokes = []
  }

  redo() {
    let redone = this.removedStrokes.pop()
    this.drawStroke(redone)
  }

  undo() {
    let undone = this.activeStrokes.pop()
    this.removedStrokes.push(undone)
    this.redraw()
  }

  private redraw() {
    this.ctx!.clearRect(0, 0, this.size, this.size)
    this.activeStrokes.forEach(s => this.drawStroke(s))
  }

  private drawStroke(stroke: DrawStroke) {
    stroke.segments.forEach(segment => this.drawSegment(segment, stroke.style))
  }

  private newLine(segment : StrokeSegment) {
    this.registerStrokeSegmentInHistory(segment);
    this.drawSegment(segment, this.currentStyle)
  }

  private registerStrokeSegmentInHistory(segment: StrokeSegment) {
    if (this.activeStrokes.length) {
      let currentStroke = this.activeStrokes[this.activeStrokes.length - 1]
      currentStroke.style = Object.assign(new DrawStyle(), this.currentStyle)
      let path = currentStroke.segments
      path.push(segment)
    }
  }

  private drawSegment(segment : StrokeSegment, style: DrawStyle) {
    style.apply(this.ctx!)

    this.ctx!.beginPath()
    this.ctx!.moveTo(segment.start.x , segment.start.y)
    this.ctx!.lineTo(segment.end.x, segment.end.y)

    this.ctx!.stroke()
  }

  private toCoordinates(event: Event): DrawPoint {
    let rect = this.canvas!.nativeElement.getBoundingClientRect()
    var x, y : number
    if(event instanceof MouseEvent) {
      x = (<MouseEvent> event).clientX
      y = (<MouseEvent> event).clientY
    } else if(event instanceof TouchEvent) {
      x = (<TouchEvent> event).touches!.item(0)!.clientX
      y = (<TouchEvent> event).touches!.item(0)!.clientY
    } else {
      throwError('Unexpected event type : '+event.type)
    }
    return new DrawPoint(
        x - rect.left,
        y - rect.top
    );
  }


}

class DrawStyle {
  constructor(public lineCap: CanvasLineCap = 'round' ,
              public lineWidth: number = 3,
              public lineJoin: CanvasLineJoin = 'round',
              public strokeStyle : string = '#000000') {
  }

  apply(context : CanvasRenderingContext2D) {
    context.lineCap = this.lineCap
    context.lineWidth = this.lineWidth
    context.lineJoin = this.lineJoin
    context.strokeStyle = this.strokeStyle
  }
}

class DrawStroke {
  public segments : Array<StrokeSegment> = []

  constructor(public style : DrawStyle = new DrawStyle()) {
  }
}

class StrokeSegment {
  constructor(public start : DrawPoint, public end : DrawPoint) {

  }

}
class DrawPoint {
  constructor(public x: number, public y: number) {
  }
}
