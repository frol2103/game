import {Component} from '@angular/core';
import {LoginService} from "../../services/login.service";
import {RoomService} from "../../services/room.service";
import {Router} from "@angular/router";

@Component({
    selector: 'game-home',
    templateUrl: './game-home.component.html',
    styleUrls: ['./game-home.component.css']
})
export class GameHomeComponent {
    inviteUrl: string = ''

    constructor(public loginService: LoginService, public roomService : RoomService, private router : Router) {
    }

    start() {

    }

    createGame() {
        this.roomService.createLostInTranslationGame()
            .subscribe(game => this.router.navigate(['/create']))
    }
}

