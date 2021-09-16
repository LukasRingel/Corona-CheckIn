import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Event} from "../model/event";

@Injectable({
  providedIn: 'root'
})
export class EventService {

  baseUrl = "https://coronalogin.daemon-dev.lukasringel.dev/event/"

  constructor(private httpClient: HttpClient) {
    this.getCurrentEvent().subscribe(value => {
      console.log(JSON.stringify(value))
    })
  }

  getCurrentEvent(): Observable<Event> {
    return this.httpClient.get<Event>(this.baseUrl + "current")
  }

  registerEvent(event: Object) {
    this.httpClient.post(this.baseUrl + "register", event);
  }

}
