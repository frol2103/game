import {Injectable} from '@angular/core';
import {AuthService, User, UserAssociation} from "../../generated/api";
import {timer} from "rxjs";
import {finalize} from "rxjs/operators";
import StatusResponse = facebook.StatusResponse;


@Injectable({
  providedIn: 'root'
})
export class LoginService {
  public ready : boolean = false
  public user : User|null = null

  public constructor(private backendAuthService : AuthService) {
    timer(0, 30000).subscribe(time => this.fetchUser())
  }

  public markNotReady() {
    this.ready=false
  }

  public fetchUser() {
    this.backendAuthService.getConnectedUser()
        .pipe(finalize(() => this.ready=true))
        .subscribe(user => this.user = user, error => this.user = null)
  }

  public createUser(form: UserCreationForm) : Promise<any> {
    return this.backendAuthService.login('"'+form.name+'"')
        .toPromise()
        .finally(() => {
          this.markNotReady()
          this.fetchUser()
        })
  }

    public fbUserAssociation(response: StatusResponse) {
        return new (class Custom implements UserAssociation {
            accessToken = response.authResponse.accessToken;
            linkType = UserAssociation.LinkTypeEnum.Facebook;
        })()
    }

    public facebookLogin() {
        const _this = this;
        FB.login(function (response) {
            if (response.status === 'connected') {
                _this.backendAuthService.socialLogin(_this.fbUserAssociation(response))
                    .toPromise()
                    .finally(() => {
                        _this.markNotReady()
                        _this.fetchUser()
                        window.location.reload()
                    })
            }
        })
    }

    public linkedToFb(){
        return this.user?.linkedAccounts?.find(v => v.linkType === UserAssociation.LinkTypeEnum.Facebook);
    }
}


export class UserCreationForm {

  constructor(
      public name: string
  ) {  }

}

