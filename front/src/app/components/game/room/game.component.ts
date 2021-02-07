import {Component, OnInit} from '@angular/core';
import {LoginService, UserCreationForm} from "../../../services/login.service";
import {RoomService} from "../../../services/room.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Game, GameDescription} from "../../../../generated/api";
import StatusEnum = GameDescription.StatusEnum;

@Component({
    selector: 'game-home',
    templateUrl: './game.component.html',
    styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {

    constructor(public loginService: LoginService, public roomService : RoomService, private route: ActivatedRoute, private router: Router) {
    }

    ngOnInit(): void {
        let gameUuid = this.route.snapshot.queryParams.game
        if(gameUuid) {
            console.log('Will join game with id from url : '+gameUuid)
            this.roomService.join(gameUuid)
                .toPromise()
                .then(game => this.redirectIfNeeded(game))
        } else {
            console.log('No game uuid in url, will stay in current game : '+this.roomService.game)
            if(this.roomService.game) {
                this.redirectIfNeeded(this.roomService.game)
            } else {
                this.roomService.goToLatestGame()
                    .then(game => this.redirectIfNeeded(game))
            }
        }
    }

    private redirectIfNeeded(game: Game | null) {
        if(game?.description?.status == "toStart" && game?.users?.filter(u => u.id == this.loginService.user?.id && u.canAdministrageGame).length) {
            this.router.navigate(['/create'])
        }
    }

    isInLobbyBeforeGame() {
        return this.roomService.ready && this.roomService.game?.description?.status == StatusEnum.ToStart
    }

    isInLobbyAfterGame() {
        return this.roomService.ready && this.roomService.game?.description?.status == StatusEnum.Finished
    }

    isInLobbyDuringGame() {
        return this.roomService.ready && this.roomService.game?.description?.status == StatusEnum.InPlay
    }

}


