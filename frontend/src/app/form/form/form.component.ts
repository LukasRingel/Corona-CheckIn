import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {EventService} from "../../service/event.service";

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  eventName = "";

  checkTruth = false;
  checkRisk = false;
  checkData = false;

  vaccinated = false;
  recovered = false;

  genders = [
    "Männlich",
    "Weiblich",
    "Keine Angabe"
  ]

  coronaForm = this.builder.group({
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

  constructor(private builder: FormBuilder, private eventService: EventService) {
    eventService.getCurrentEvent().subscribe(value => {
      this.eventName = value.name
    })
  }

  ngOnInit(): void {
  }

  submit(): void {
    alert(JSON.stringify(this.coronaForm.value))
  }

  toggleCheckTruth(): void {
    this.checkTruth = !this.checkTruth
  }

  toggleCheckRisk(): void {
    this.checkRisk = !this.checkRisk
  }

  toggleCheckData(): void {
    this.checkData = !this.checkData
  }

  toggleRecovered(): void {
    this.recovered = !this.recovered
  }

  toggleVaccinated(): void {
    this.vaccinated = !this.vaccinated
  }

}
