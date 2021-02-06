import { Component } from '@angular/core';
import {Title} from "@angular/platform-browser";
import {LabelsService} from "./services/labels.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private titleService: Title, private labelsService: LabelsService) {
    titleService.setTitle(labelsService.translate("page_title"))
  }
}
