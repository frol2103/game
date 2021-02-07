import {Component} from '@angular/core';
import {LoginService} from "../../services/login.service";

@Component({
    selector: 'game-home',
    templateUrl: './game-home.component.html',
    styleUrls: ['./game-home.component.css']
})
export class GameHomeComponent {
    inviteUrl: string = ''

    constructor(public loginService: LoginService) {
    }

    start() {

    }
}

