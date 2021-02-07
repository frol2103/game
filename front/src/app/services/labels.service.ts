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
  }

  public translate(key: string) : string {
    if(this.labels[key]) {
      return this.labels[key]
    } else {
      return "To be translated : "+key
    }
  }

}

