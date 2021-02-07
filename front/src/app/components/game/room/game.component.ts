import {Component} from '@angular/core';
import {LoginService, UserCreationForm} from "../../../services/login.service";

@Component({
    selector: 'game-home',
    templateUrl: './game.component.html',
    styleUrls: ['./game.component.css']
})
export class GameComponent {

    constructor(public loginService: LoginService) {
    }

}


