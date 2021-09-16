import {Guest} from "./guest";

export interface Event {

  uniqueId: string
  name: string
  startTimeStamp: number
  host: Guest

}
