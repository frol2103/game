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
  public userPrefs : any = {}

  public constructor(private backendAuthService : AuthService) {
    timer(0, 30000).subscribe(time => this.fetchUser())
  }

  public markNotReady() {
    this.ready=false
  }

  public fetchUser() {
    this.backendAuthService.getConnectedUser()
        .pipe(finalize(() => this.ready=true))
        .subscribe(user => this.receivedUser(user),
                error => this.receivedError(error))
  }

  public createUser(form: UserCreationForm) : Promise<any> {
    this.markNotReady()
    return this.backendAuthService.login('"'+form.name+'"')
        .toPromise()
        .finally(() => {
          this.fetchUser()
        })
  }

  private receivedUser(user: User) {
    this.ready=true
    this.user = user
  }

  private receivedError(error: any) {
    if(error.status && error.status == 401) {
      this.ready=true
    }
    if(error.status && error.status == 500) {
      this.ready=true
    //  todo this should not be ok and should result in an error message
    }
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

