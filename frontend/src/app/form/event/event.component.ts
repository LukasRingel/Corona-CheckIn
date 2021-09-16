import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {EventService} from "../../service/event.service";
import {CoronaState} from "../../model/coronaState";

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})
export class EventComponent implements OnInit {

  eventForm = this.builder.group({
    name: ['', Validators.required],
    person: this.builder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      gender: ['', Validators.required],
      birthDate: ['', Validators.required],
      phoneNumber: ['', Validators.required]
    }),
    address: this.builder.group({
      street: ['', Validators.required],
      postCode: ['', Validators.required],
      city: ['', Validators.required],
    })
  });

  genders = [
    "Männlich",
    "Weiblich",
    "Keine Angabe"
  ]

  coronaState = CoronaState.NOTHING

  constructor(private builder: FormBuilder, private eventService: EventService) {
  }

  ngOnInit(): void {
  }

  submit(): void {
    this.eventService.registerEvent({
      eventName: this.eventForm.value.name,
      hostName: this.eventForm.value.person.firstName,
      hostLastName: this.eventForm.value.person.lastName,
      hostGender: this.genderIdByFormInput(this.eventForm.value.person.gender),
      hostBirthDate: this.eventForm.value.person.birthDate,
      hostPhoneNumber: this.eventForm.value.person.phoneNumber,
      hostStreet: this.eventForm.value.address.street,
      hostPostCode: this.eventForm.value.address.postCode,
      hostCity: this.eventForm.value.address.city,
      hostCorona: this.coronaIdByFormInput()
    })
  }

  genderIdByFormInput(name: string): number {
    if (name === "Männlich") return 0;
    if (name === "Weiblich") return 1;
    return 2;
  }

  coronaIdByFormInput(): number {
    if (this.coronaState == CoronaState.VACCINATED) return 0;
    if (this.coronaState == CoronaState.RECOVERED) return 1;
    return 2;
  }

  updateCoronaStateToVaccinated() {
    this.coronaState = CoronaState.VACCINATED;
  }

  updateCoronaStateToRecovered() {
    this.coronaState = CoronaState.RECOVERED;
  }

}
