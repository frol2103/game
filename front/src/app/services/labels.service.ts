import { Injectable } from '@angular/core';
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class LabelsService {

  private labels : any = {
    'page_title': "Paperless games"
  }

  public translate(key: string) : string {
    return this.labels[key]
  }

}

