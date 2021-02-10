import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'wait-for',
  templateUrl: './wait-for.component.html',
  styleUrls: ['./wait-for.component.css']
})
export class WaitForComponent implements OnInit {
  @Input("condition") condition : boolean = false
  @Input("spinner-class") spinnerClass : string = ''

  constructor() { }

  ngOnInit(): void {
  }

}
