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

@NgModule({
    declarations: [
        AppComponent,
        TranslatePipe,
        UserLoginComponent,
        GameHomeComponent
    ],
    imports: [
        FormsModule,
        BrowserModule,
        NgbModule,
        HttpClientModule,
        ApiModule
    ],
    providers: [
        Title,
        {provide: BASE_PATH, useValue: environment.apiBaseUrl}
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
