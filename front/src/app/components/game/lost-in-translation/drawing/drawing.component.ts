import {
  AfterViewChecked,
  AfterViewInit,
  Component,
  ElementRef,
  HostListener,
  Input,
  OnInit,
  ViewChild
} from '@angular/core';
import {fromEvent, Observable, throwError} from "rxjs";
import {pairwise, switchMap, takeUntil} from "rxjs/operators";
import {LitImageProvider} from "../lost-in-translation.component";
import {LitService} from "../../../../services/lit.service";

const savedLitDrawingKey = 'litDrawing';

@Component({
  selector: 'lit-drawing',
  templateUrl: './drawing.component.html',
  styleUrls: ['./drawing.component.css']
})
export class DrawingComponent implements  AfterViewInit,AfterViewChecked, LitImageProvider {
  @ViewChild('drawingcanvas') canvas: ElementRef<HTMLCanvasElement> | undefined
  @ViewChild('drawingcanvasparent') canvasParent: ElementRef | undefined
  size = 600
  lastCheckSize = 600

  currentTool: (Event) => DrawElement = e => this.strokeTool(e)

  activeDrawingElements : Array<DrawElement> = []
  removedDrawingElements : Array<DrawElement> = []
  currentStyle : DrawStyle = new DrawStyle()

  availablePenSizes = [
        2,
        3,
        6,
        10,
        20,
        50
      ]

  availableColors = [
    '#000000',
    '#ff0000',
    '#ffff00',
    '#029102',
    '#0000ff',
    '#ee02eb',
    '#6d6d6d',
    '#604026',
    '#ffffff',
  ]
  colorPickerDropdownOpen: boolean = false
  sizePickerDropdownOpen: boolean = false
  toolsDropdownOpen: boolean = false

  constructor(private lostInTranslationService: LitService) {

  }


  @HostListener('window:resize', ['$event'])
  onResize(event) {
    console.log("detected window resize " )
    this.setCanvasSizeForWindowSize()
  }

  strokeTool(event : Event) : DrawStroke {
    let stroke = new DrawStroke(this.currentStyle)
    // let startPoint = this.toCoordinates(event)
    // stroke.onPointerMove(startPoint, startPoint, this.getCanvas())
    return stroke
  }

  relativeSizePercents(size: number) {
    return 100 * size / this.availablePenSizes[this.availablePenSizes.length - 1]
  }

  private getCanvas() {
    return this.canvas.nativeElement!;
  }

  loadSavedDrawing() {
    let stored = localStorage.getItem(this.localStorageKey())
    if(stored && stored.length) {
      let storedObjects = JSON.parse(stored)
      this.activeDrawingElements = storedObjects.flatMap(o => {
        if(o.type == 'stroke') {
          let stroke = new DrawStroke()
          stroke.segments = o.segments
          stroke.style = new DrawStyle()
          Object.assign(stroke.style, o.style)
          return stroke
        } else {
          return []
        }
      })
      this.removedDrawingElements = []
      this.redraw()
    }
  }

  isDrawingReady(): boolean {
    return this.activeDrawingElements &&  this.activeDrawingElements.length > 0
  }

  saveDrawing() {
    localStorage.setItem(this.localStorageKey(), JSON.stringify(this.activeDrawingElements))
  }

  private localStorageKey() {
    return savedLitDrawingKey+'-'+this.lostInTranslationService.game.game.description.uuid!;
  }

  public clearSavedDrawing() {
    localStorage.setItem(this.localStorageKey(), '')
  }

  ngAfterViewInit(): void {
    this.availablePenSizes.sort((a: number, b: number) => a - b)

    this.captureStrokes('mousedown', 'mousemove', 'mouseup', 'mouseleave')
    this.captureStrokes('touchstart', 'touchmove', 'touchend', 'touchcancel')

    setTimeout(() => this.setCanvasSizeForWindowSize(), 100)
    setTimeout(() => this.loadSavedDrawing(), 100)
  }

  ngAfterViewChecked(): void {
    if(this.lastCheckSize != this.size) {
      console.log("Will redraw after size changed from "+this.lastCheckSize+" to "+this.size)
      this.redraw()
    }
    this.lastCheckSize = this.size
  }

  private setCanvasSizeForWindowSize() {
    let windowHeight = window.innerHeight
    let windowWidth = window.innerWidth
    var canvasParentWidth = this.getCanvas().parentElement!.getBoundingClientRect().width
    let maxWidth = Math.min(canvasParentWidth-30, windowWidth-20);
    let maxHeight = windowHeight - 100;
    let newSize = Math.round(Math.min(maxHeight, maxWidth))
    let oldSize = this.size
    if(oldSize != newSize && newSize > 0) {
      this.size = newSize
      this.getCanvas().width = this.size
      this.getCanvas().height = this.size
    }
  }

  captureDrawingAsBlob() : Promise<Blob> {
    return new Promise<Blob>((resolve, reject) => this.getCanvas().toBlob((b : Blob) => b ? resolve(b!) : reject()))
  }

  private fromEvent(eventType : string) : Observable<Event> {
    return fromEvent(this.getCanvas(), eventType)
  }

  private captureStrokes(startEvent: string, moveEvent: string, stopEvent: string, cancelEvent: string) {
    this.fromEvent(startEvent)
        .subscribe(startDrawingEvent => this.activeDrawingElements.push(this.currentTool(startDrawingEvent)))

    this.fromEvent(startEvent)
        .pipe(
            switchMap(event => this.fromEvent(moveEvent).pipe(takeUntil(this.fromEvent(stopEvent)), takeUntil(this.fromEvent(cancelEvent)), pairwise()))
        )
        .subscribe(stroke=> this.registerPointerMove(this.toCoordinates(stroke[0]), this.toCoordinates(stroke[1])))

    this.fromEvent(stopEvent).subscribe(event => this.finishedElement(event))
    this.fromEvent(cancelEvent).subscribe(event => this.finishedElement(event))
  }

  clear() {
    this.getCanvas().getContext('2d')!.clearRect(0, 0, this.size, this.size)
    this.removedDrawingElements = this.activeDrawingElements.reverse()
    this.activeDrawingElements = []
  }

  redo() {
    let redone = this.removedDrawingElements.pop()
    this.drawElementOnCanvas(redone)
  }

  undo() {
    let undone = this.activeDrawingElements.pop()
    this.removedDrawingElements.push(undone)
    this.redraw()
  }

  private redraw() {
    this.getCanvas().getContext('2d')!.clearRect(0, 0, this.size, this.size)
    console.log("Will redraw "+this.activeDrawingElements.length+" strokes")
    this.activeDrawingElements.forEach(s => this.drawElementOnCanvas(s))
  }

  private drawElementOnCanvas(element: DrawElement) {
    element.drawOnCanvas(this.getCanvas())
  }

  private registerPointerMove(from: DrawPoint, to : DrawPoint) {
    if (this.activeDrawingElements.length) {
      let currentElement = this.activeDrawingElements[this.activeDrawingElements.length - 1]
      currentElement.style = Object.assign(new DrawStyle(), this.currentStyle)
      currentElement.onPointerMove(from, to, this.getCanvas()!)
    }
  }

  private toCoordinates(event: Event): DrawPoint {
    let rect = this.getCanvas().getBoundingClientRect()
    var x, y : number
    if(event instanceof MouseEvent) {
      event.preventDefault()
      x = (<MouseEvent> event).clientX
      y = (<MouseEvent> event).clientY
    } else if(event instanceof TouchEvent) {
      event.preventDefault()
      x = (<TouchEvent> event).touches!.item(0)!.clientX
      y = (<TouchEvent> event).touches!.item(0)!.clientY
    } else {
      throwError('Unexpected event type : '+event.type)
    }
    return new DrawPoint(
        (x - rect.left) / this.getCanvas().width,
        (y - rect.top) / this.getCanvas().height
    );
  }


  toggleMenu(name : string) {
    let previousValue = this[name]
    this.closeMenus()
    this[name] = !previousValue
  }

  closeMenus() {
    this.colorPickerDropdownOpen = false
    this.sizePickerDropdownOpen = false
    this.toolsDropdownOpen = false
  }

  private finishedElement(event: Event) {
    this.saveDrawing()
  }

  confirmDrawingSent(): void {
    this.clearSavedDrawing()
  }
}

class DrawStyle {
  referenceCanvasWidth = 600

  constructor(public lineCap: CanvasLineCap = 'round' ,
              public lineWidth: number = 3,
              public lineJoin: CanvasLineJoin = 'round',
              public strokeStyle : string = '#000000') {
  }

  apply(context : CanvasRenderingContext2D) {
    context.lineCap = this.lineCap
    context.lineWidth = this.lineWidth * context.canvas.width / this.referenceCanvasWidth
    context.lineJoin = this.lineJoin
    context.strokeStyle = this.strokeStyle
  }
}

interface DrawElement {
  style : DrawStyle
  drawOnCanvas(nativeElement: HTMLCanvasElement)

  onPointerMove(from: DrawPoint, to: DrawPoint, canvas: HTMLCanvasElement)
}

class DrawStroke implements DrawElement {
  public type: string = 'stroke'
  public segments : Array<StrokeSegment> = []

  constructor(public style : DrawStyle = new DrawStyle()) {
  }

  onPointerMove(from: DrawPoint, to: DrawPoint, canvas: HTMLCanvasElement) {
    let segment = new StrokeSegment(from, to)
    this.segments.push(segment)
    this.drawSegment(segment, canvas)
  }

  drawOnCanvas(canvas: HTMLCanvasElement): void {
    this.segments.forEach(segment => this.drawSegment(segment, canvas))
  }

  drawSegment(segment: StrokeSegment, canvas: HTMLCanvasElement): void {
      let ctx = canvas.getContext('2d')
      this.style.apply(ctx)

      let canvasWidth = canvas!.width
      let canvasHeight = canvas!.height
      ctx!.beginPath()
      ctx!.moveTo(segment.start.xRelativeToCanvasWidth*canvasWidth , segment.start.yRelativeToCanvasHeight*canvasHeight)
      ctx!.lineTo(segment.end.xRelativeToCanvasWidth*canvasWidth, segment.end.yRelativeToCanvasHeight*canvasHeight)

      ctx!.stroke()
  }
}

class StrokeSegment {
  constructor(public start : DrawPoint, public end : DrawPoint) {

  }

}
class DrawPoint {
  constructor(public xRelativeToCanvasWidth: number,
              public yRelativeToCanvasHeight: number) {
  }
}
