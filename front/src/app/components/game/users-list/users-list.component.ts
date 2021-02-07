import { Component, OnInit } from '@angular/core';
import {RoomService} from "../../../services/room.service";

@Component({
  selector: 'users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit {

  constructor(public roomService : RoomService) { }

  ngOnInit(): void {
  }

  isReady() {
    return this.roomService.game && this.roomService.ready
  }
}
