import {Component, OnInit} from '@angular/core';
import {LoginService, UserCreationForm} from "../../../services/login.service";
import {RoomService} from "../../../services/room.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Game} from "../../../../generated/api";

@Component({
    selector: 'game-home',
    templateUrl: './game.component.html',
    styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {

    constructor(public loginService: LoginService, public roomService : RoomService, private route: ActivatedRoute, private router: Router) {
    }

    ngOnInit(): void {
        this.roomService.refreshCurrentGame().then(game => this.joinIfNoGameInProgress(game))
    }

    private joinIfNoGameInProgress(game: Game | null) {
        if(!game) {
            let gameUuid = this.route.snapshot.queryParams.game
            if(gameUuid) {
                console.log('Will join game '+gameUuid)
                this.roomService.join(gameUuid)
            }
        } else {
            //todo: if alreayd in a game maybe prompt the user to leave current game and join the new one instead
            console.log("The user is already in game "+game.description?.uuid)

            if(game.description?.status == "toStart" && game.users?.filter(u => u.id == this.loginService.user?.id && u.canAdministrageGame).length) {
                this.router.navigate(['/create'])
            }
        }
    }

}


