import {Component} from '@angular/core';
import {LoginService, UserCreationForm} from "../../services/login.service";

let vowels = 'aeiouy';
let conso = 'bcfgjklmnprstxz';

@Component({
    selector: 'user-login',
    templateUrl: './user-login.component.html',
    styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent {
    form = new UserCreationForm(randomString(1, conso)+randomString(1, vowels)+randomString(1, conso)+randomString(2, vowels))

    constructor(public loginService: LoginService) {
    }

    public login() {
        this.loginService.createUser(this.form)
    }

    public facebookLogin() {
        this.loginService.facebookLogin()
    }
}

function randomString(length: number, chars: string) {
    var result = '';
    for (var i = length; i > 0; --i) result += chars[Math.floor(Math.random() * chars.length)];
    return result;
}


