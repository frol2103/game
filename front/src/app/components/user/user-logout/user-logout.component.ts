import { Component, OnInit } from '@angular/core';
import {Location} from '@angular/common';
import {AuthService} from "../../../../generated/api";

@Component({
  selector: 'user-logout',
  templateUrl: './user-logout.component.html',
  styleUrls: ['./user-logout.component.css']
})
export class UserLogoutComponent implements OnInit {

  constructor(
      private location: Location,
      private authService: AuthService,
  ) { }

  ngOnInit(): void {
  }

  cancel() {
    this.location.back()
  }

  logout() {
    this.authService.logout().toPromise().then(v => document.location.href=document.location.origin);
  }
}
