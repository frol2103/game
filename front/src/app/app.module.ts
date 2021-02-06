import {BrowserModule, Title} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {FormsModule} from '@angular/forms';
import {TranslatePipe} from './pipes/translate.pipe';

@NgModule({
    declarations: [
        AppComponent,
        TranslatePipe
    ],
    imports: [
        FormsModule,
        BrowserModule,
        NgbModule
    ],
    providers: [Title],
    bootstrap: [AppComponent]
})
export class AppModule {
}
