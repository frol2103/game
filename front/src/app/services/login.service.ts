import { Injectable } from '@angular/core';
import {AuthService, User} from "../../generated/api";
import {timer} from "rxjs";
import {finalize} from "rxjs/operators";


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

  public createUser(form: UserCreationForm) {
    this.backendAuthService.login('"'+form.name+'"')
        .toPromise()
        .finally(() => {
          this.markNotReady()
          this.fetchUser()
        })
  }
}


export class UserCreationForm {

  constructor(
      public name: string
  ) {  }

}

