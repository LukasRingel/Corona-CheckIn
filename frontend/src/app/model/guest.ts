import {Gender} from "./gender";
import {Address} from "./address";
import {CoronaState} from "./coronaState";

export interface Guest {

  uniqueId: string
  eventId: string
  firstName: string
  lastName: string
  gender: Gender
  birthDate: string
  phoneNumber: string
  address: Address
  coronaState: CoronaState

}
