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
import {HomeComponent} from "./components/game/home.component";
import {RouterModule, Routes} from "@angular/router";
import {GameCreationComponent} from "./components/game/room/creation/game-creation.component";
import {GameComponent} from "./components/game/room/game.component";
import { UsersListComponent } from './components/game/users-list/users-list.component';
import { LostInTranslationComponent } from './components/game/lost-in-translation/lost-in-translation.component';
import { LostInTranslationFinishedComponent } from './components/game/lost-in-translation/finished/lost-in-translation-finished.component';
import { ProfileComponent } from './components/user/profile/profile.component';

const routes: Routes = [
    { path: 'home', component: HomeComponent },
    { path: 'profile', component: ProfileComponent },
    { path: 'join', component: GameComponent },
    { path: 'current', component: GameComponent },
    { path: '**', component: HomeComponent }
]

@NgModule({
    declarations: [
        AppComponent,
        TranslatePipe,
        UserLoginComponent,
        HomeComponent,
        GameCreationComponent,
        GameComponent,
        UsersListComponent,
        LostInTranslationComponent,
        LostInTranslationFinishedComponent,
        ProfileComponent
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
