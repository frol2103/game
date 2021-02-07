import { Component } from '@angular/core';
import {Title} from "@angular/platform-browser";
import {LabelsService} from "./services/labels.service";
import {LoginService} from "./services/login.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private titleService: Title, private labelsService: LabelsService, public loginService:LoginService) {
    titleService.setTitle(labelsService.translate("page_title"))
  }

  isReady() {
    return this.loginService.ready
  }
}
