import {Component, OnInit} from '@angular/core';
import {LoginService, UserCreationForm} from "../../../../services/login.service";
import {RoomService} from "../../../../services/room.service";
import {throwError} from "rxjs";
import {Router} from "@angular/router";

@Component({
    selector: 'game-creation',
    templateUrl: './game-creation.component.html',
    styleUrls: ['./game-creation.component.css']
})
export class GameCreationComponent implements OnInit {
    inviteUrl: string = window.location.protocol + "//" + window.location.host+'/join?game='

    constructor(public loginService: LoginService, public roomService : RoomService, private router : Router) {
    }

    ngOnInit(): void {
        if(!this.roomService.game) {
            throwError('An active game is mandatory to access the game creation interface')
        }
        let game = this.roomService.game!;

        this.inviteUrl=this.inviteUrl + game.description?.uuid
    }

    isReady() {
        return this.roomService.ready
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
        this.roomService.startGame()
    }
}


