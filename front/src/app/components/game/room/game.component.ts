import {Component, OnDestroy, OnInit} from '@angular/core';
import {LoginService} from "../../../services/login.service";
import {RoomService} from "../../../services/room.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Game, GameDescription} from "../../../../generated/api";
import StatusEnum = GameDescription.StatusEnum;

@Component({
    selector: 'game-home',
    templateUrl: './game.component.html',
    styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit, OnDestroy {
    isAdmin: boolean = false

    constructor(public loginService: LoginService, public roomService: RoomService, private route: ActivatedRoute, private router: Router) {
    }

    ngOnDestroy(): void {
        //warning: if we need room or user slist info outside of this component this will need to be removed, currently we don't use ngroute for the game interfaces so it works
        this.roomService.destroy()
    }

    ngOnInit(): void {
        let gameUuid = this.route.snapshot.queryParams.game
        if (gameUuid) {
            console.log('Will join game with id from url : ' + gameUuid)
            this.roomService.join(gameUuid)
                .then(game => this.updateRole(game!))
                .catch(error => {
                    console.log('Error: this game doe snot exists : '+gameUuid)
                    this.router.navigate(['/home'])
                })
        } else {
            if (this.roomService.game) {
                console.log('No game uuid in url, will stay in current game : ' + this.roomService.game)
                this.updateRole(this.roomService.game)
            } else {
                console.log('No game uuid in url, send user back to home to select a game')
                this.router.navigate(['/home'])
            }
        }
    }

    private updateRole(game: Game) {
        this.isAdmin = game.users!.filter(u => u.uuid == this.loginService.user?.uuid && u.canAdministrageGame).length > 0
    }

    isInLobbyBeforeGame() {
        return this.roomService.ready && this.roomService.game?.description?.status == StatusEnum.ToStart
    }

    isInLobbyDuringGame() {
        return this.roomService.ready && this.roomService.game?.description?.status != StatusEnum.ToStart
    }

}


