import { Component } from '@angular/core';
import {Title} from "@angular/platform-browser";
import {LabelsService} from "./services/labels.service";
import {LoginService} from "./services/login.service";
import {RoomService} from "./services/room.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  menuOpen: boolean = false;

  constructor(private titleService: Title, private labelsService: LabelsService, public loginService:LoginService, private router: Router) {
    titleService.setTitle(labelsService.translate("page_title"))
    router.events.subscribe(routerEvent => this.menuOpen = false)
  }

  isReady() {
    return this.loginService.ready
  }

  displayHomeButton() {
    return !this.router.isActive('/home', false) && !this.router.isActive('/logout', false)
  }

  toggleMenu() {
    this.menuOpen = !this.menuOpen
  }
}
