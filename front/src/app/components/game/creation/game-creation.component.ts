import {Component} from '@angular/core';
import {LoginService, UserCreationForm} from "../../../services/login.service";

@Component({
    selector: 'game-home',
    templateUrl: './game-creation.component.html',
    styleUrls: ['./game-creation.component.css']
})
export class GameCreationComponent {
    inviteUrl: string = 'http://todo?game=todo'

    constructor(public loginService: LoginService) {
    }

    copyUrlToClipboard() {
        console.log("copy "+this.inviteUrl)
        var input = document.querySelector('#copy-input');
        if(input) {
            // @ts-ignore
            input.select();
            // @ts-ignore
            input.setSelectionRange(0, input.value.length + 1);
            document.execCommand('copy')
        }
    }

    start() {

    }
}


