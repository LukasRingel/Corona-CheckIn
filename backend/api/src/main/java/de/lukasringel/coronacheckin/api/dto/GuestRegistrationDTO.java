package de.lukasringel.coronacheckin.api.dto;

/**
 * This data transfer object contains our registration data from the frontend
 *
 * @param firstName   - the first name of the guest
 * @param lastName    - the last name of the guest
 * @param gender      - the gender ordinal of the guest
 * @param birthDate   - the date of birth of the guest
 * @param phoneNumber - the phone number of the guest
 * @param street      - the address street of the guest
 * @param postCode    - the address postCode of the guest
 * @param city        - the address city of the guest
 * @param coronaState - the corona state ordinal of the guest
 */

public record GuestRegistrationDTO(String firstName,
                                   String lastName,
                                   int gender,
                                   String birthDate,
                                   String phoneNumber,
                                   String street,
                                   int postCode,
                                   String city,
                                   int coronaState) {
}
