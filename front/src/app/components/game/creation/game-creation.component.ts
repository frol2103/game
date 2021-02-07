import {Component} from '@angular/core';
import {LoginService, UserCreationForm} from "../../../services/login.service";

@Component({
    selector: 'game-home',
    templateUrl: './game-creation.component.html',
    styleUrls: ['./game-creation.component.css']
})
export class GameCreationComponent {
    inviteUrl: string = ''

    constructor(public loginService: LoginService) {
    }

    start() {

    }
}


