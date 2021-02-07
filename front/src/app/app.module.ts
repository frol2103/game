import {BrowserModule, Title} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {FormsModule} from '@angular/forms';
import {TranslatePipe} from './pipes/translate.pipe';
import {ApiModule, BASE_PATH} from "../generated/api";
import {HttpClientModule} from "@angular/common/http";
import {environment} from "../environments/environment";
import {UserLoginComponent} from "./components/user/user-login.component";
import {GameHomeComponent} from "./components/game/game-home.component";
import {RouterModule, Routes} from "@angular/router";
import {GameCreationComponent} from "./components/game/creation/game-creation.component";
import {GameComponent} from "./components/game/room/game.component";

const routes: Routes = [
    { path: 'home', component: GameHomeComponent },
    { path: 'create', component: GameCreationComponent },
    { path: 'join', component: GameComponent },
    { path: '**', component: GameHomeComponent }
]

@NgModule({
    declarations: [
        AppComponent,
        TranslatePipe,
        UserLoginComponent,
        GameHomeComponent,
        GameCreationComponent,
        GameComponent
    ],
    imports: [
        FormsModule,
        BrowserModule,
        NgbModule,
        HttpClientModule,
        ApiModule,
        RouterModule.forRoot(routes)
    ],
    exports: [RouterModule],
    providers: [
        Title,
        {provide: BASE_PATH, useValue: environment.apiBaseUrl}
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
