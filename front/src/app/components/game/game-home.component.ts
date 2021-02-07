import {Component, OnInit} from '@angular/core';
import {LoginService} from "../../services/login.service";
import {RoomService} from "../../services/room.service";
import {Router} from "@angular/router";
import {GameDescription} from "../../../generated/api";

@Component({
    selector: 'game-home',
    templateUrl: './game-home.component.html',
    styleUrls: ['./game-home.component.css']
})
export class GameHomeComponent implements OnInit {
    inviteUrl: string = ''
    games : Array<GameDescription> = []

    constructor(public loginService: LoginService, public roomService : RoomService, private router : Router) {
    }

    ngOnInit(): void {
        this.roomService.getAllGames().then(games => this.games = games)
    }


    createGame() {
        this.roomService.createLostInTranslationGame()
            .subscribe(game => this.router.navigate(['/create']))
    }
}

