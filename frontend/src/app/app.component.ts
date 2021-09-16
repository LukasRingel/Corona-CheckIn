import {Component, OnInit} from '@angular/core';
import {EventService} from "./service/event.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  title = 'frontend';

  isEventActive = true

  constructor(private eventService: EventService) {
  }

  ngOnInit() {
    this.eventService.getCurrentEvent().subscribe(value => {
      this.isEventActive = value != null
    })
  }

}
