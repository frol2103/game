import { Component, OnInit } from '@angular/core';
import {AuthService, UserAssociation} from "../../../../generated/api";
import {LoginService} from "../../../services/login.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(
      private backendAuthService: AuthService,
      public loginService: LoginService,
  ) { }

  ngOnInit(): void {

  }

  linkFacebook() {
    const _this= this;
    FB.login(function(response){
      if (response.status === 'connected') {
        _this.backendAuthService.linkToExternalAccount(_this.loginService.fbUserAssociation(response)).toPromise().then(v =>
            _this.loginService.user = v);

      }
    })
  }

}
