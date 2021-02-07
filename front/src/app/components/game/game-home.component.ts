import {Component} from '@angular/core';
import {LoginService, UserCreationForm} from "../../services/login.service";

@Component({
    selector: 'game-home',
    templateUrl: './game-home.component.html',
    styleUrls: ['./game-home.component.css']
})
export class GameHomeComponent {
    form = new UserCreationForm(randomString(3, 'abcdefghijklmnopqrstuvwxyz')+'-'+randomString(3, '0123456789'))

    constructor(public loginService: LoginService) {
    }

    public login() {
        this.loginService.createUser(this.form)
    }
}

function randomString(length: number, chars: string) {
    var result = '';
    for (var i = length; i > 0; --i) result += chars[Math.floor(Math.random() * chars.length)];
    return result;
}


