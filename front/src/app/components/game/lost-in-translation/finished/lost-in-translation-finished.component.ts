import { Component, OnInit } from '@angular/core';
import {LitService} from "../../../../services/lit.service";

@Component({
  selector: 'lost-in-translation-finished',
  templateUrl: './lost-in-translation-finished.component.html',
  styleUrls: ['./lost-in-translation-finished.component.css']
})
export class LostInTranslationFinishedComponent implements OnInit {

  constructor(public lostInTranslationService: LitService) { }

  ngOnInit(): void {
  }


}
