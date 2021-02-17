import {Component, OnInit} from '@angular/core';
import {LoginService} from "../../services/login.service";
import {RoomService} from "../../services/room.service";
import {Router} from "@angular/router";
import {Game, GameDescription} from "../../../generated/api";
import GameTypeEnum = GameDescription.GameTypeEnum;

@Component({
    selector: 'game-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
    createDisabled: boolean = false
    inviteUrl: string = ''
    games : Array<GameDescription> = []
    gameTypes = Object.keys(GameDescription.GameTypeEnum).map(k => GameDescription.GameTypeEnum[k])
    chosenType : GameDescription.GameTypeEnum = GameDescription.GameTypeEnum.LostInTranslation
    pageStart: number = 0
    pageSize: number = 5

    constructor(public loginService: LoginService, public roomService : RoomService, private router : Router) {
    }

    ngOnInit(): void {
        this.roomService.getAllGames().then(games => this.games = games)
    }


    createGame() {
        console.log("Button clicked to create new game")
        this.createDisabled = true
        this.roomService.createGame(this.chosenType)
            .then(game => this.navigateToGamePage(game.description?.uuid))
    }

    private navigateToGamePage(uuid: string | undefined ) {
        return this.router.navigate(['/current'], {queryParams: {game: uuid}});
    }

    openGame(uuid: string) {
        this.navigateToGamePage(uuid)
    }
}

