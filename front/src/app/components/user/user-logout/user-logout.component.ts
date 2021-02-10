import { Component, OnInit } from '@angular/core';
import {Location} from '@angular/common';

@Component({
  selector: 'user-logout',
  templateUrl: './user-logout.component.html',
  styleUrls: ['./user-logout.component.css']
})
export class UserLogoutComponent implements OnInit {

  constructor(private location: Location) { }

  ngOnInit(): void {
  }

  cancel() {
    this.location.back()
  }

  logout() {
    // todo when backend allows logouts
  }
}
