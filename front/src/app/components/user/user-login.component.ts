import {Component} from '@angular/core';
import {LoginService, UserCreationForm} from "../../services/login.service";

@Component({
    selector: 'user-login',
    templateUrl: './user-login.component.html',
    styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent {
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


