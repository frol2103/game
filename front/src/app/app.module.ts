import {BrowserModule, Title} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
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
import { UserLogoutComponent } from './components/user/user-logout/user-logout.component';
import { PanelComponent } from './components/utils/panel/panel.component';
import { WaitForComponent } from './components/utils/wait-for/wait-for.component';
import { DrawingComponent } from './components/game/lost-in-translation/drawing/drawing.component';
import { DefineItLyComponent } from './components/game/define-it-ly/define-it-ly.component';
import { CameraSnapshotComponent } from './components/game/lost-in-translation/camera-snapshot/camera-snapshot.component';
import { DilFinishedRoundComponent } from './components/game/define-it-ly/dil-finished-round/dil-finished-round.component';
import { ImageUploadComponent } from './components/game/lost-in-translation/image-upload/image-upload.component';
import {ImageCropperModule} from "ngx-image-cropper";
import {OrderModule} from "ngx-order-pipe";

const routes: Routes = [
    { path: 'logout', component: UserLogoutComponent },
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
        ProfileComponent,
        UserLogoutComponent,
        PanelComponent,
        WaitForComponent,
        DrawingComponent,
        DefineItLyComponent,
        CameraSnapshotComponent,
        DilFinishedRoundComponent,
        ImageUploadComponent,
    ],
    imports: [
        FormsModule,
        BrowserModule,
        HttpClientModule,
        ApiModule,
        RouterModule.forRoot(routes),
        ImageCropperModule,
        OrderModule
    ],
    exports: [RouterModule],
    providers: [
        Title,
        TranslatePipe,
        {provide: BASE_PATH, useValue: environment.apiBaseUrl}
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
