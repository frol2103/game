import {Component, OnInit} from '@angular/core';
import {LoginService, UserCreationForm} from "../../../services/login.service";
import {RoomService} from "../../../services/room.service";
import { ActivatedRoute } from "@angular/router";

@Component({
    selector: 'game-home',
    templateUrl: './game.component.html',
    styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {

    constructor(public loginService: LoginService, public roomService : RoomService, private route: ActivatedRoute) {
    }

    ngOnInit(): void {
        this.roomService.refreshCurrentGame()
        if(!this.roomService.game) {
            let gameUuid = this.route.snapshot.queryParams.game
            if(gameUuid) {
                console.log('Will join game '+gameUuid)
                this.roomService.join(gameUuid)
            }
        }
    }

}


