import { Injectable } from '@angular/core';
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class LabelsService {

  private labels : any = {
    'page_title': "Paperless games",
    'login_form_title': "Choose a user nickname",
    'login_form_name': "Name",
    'login_button': "Use this name",
    'game_creation_title': "Start playing",
    'invite_people_title': "Invite friends",
    'game_creation_description': "Your friends need to join the game before starting it.",
    'game_creation_invite_url_label': "Send this url to your friend to join your game : ",
    'copy': "Copy to clipboard",
    'gstart_game_title': "Start the game",
    'people_in_the_room': "These are the current players : ",
    'start_the_game_descr': "Wait for everyone to be listed. Once you start the game your friends won't be able to join anymore until the next game.",
    'start_game_button': "Start the game now",
    'game_joining_title': "Game lobby",
    'game_joining_description': "The game host will start the game soon.",
    'game_home_title': "Welcome",
    'create_game_button': "Create a new game",
    'lostInTranslation_label': "Lost in transcription",
    'no_game': "You are not currently in any active game. You can either create your own game and invite friends or use an invite url to join a game.",
    'active_games_list_title': "You are in these games : ",
    'toStart_label': "Game not started",
    'inPlay_label': "In progress",
    'finished_label': "Finished",
    'open_game_button': "Open",
    'game_created': "Start time",
    'game_type': "Type",
    'game_status': "Status",
    'active_games_list_below': "You can also create a new game or join another friend's game by using the invite url they sent you.",
    'go_back_button': "Go back",
    'profile_title': 'Profile',
    'link_facebook_button': 'Link to facebook',
    'social_login_title': 'Social login',
    'social_login_button': 'Connect with Facebook',
    'account_linked_to_facebook_label': 'Linked to facebook',

  }

  public translate(key: string) : string {
    if(this.labels[key]) {
      return this.labels[key]
    } else {
      return "To be translated : "+key
    }
  }

}

