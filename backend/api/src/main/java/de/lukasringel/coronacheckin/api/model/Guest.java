package de.lukasringel.coronacheckin.api.model;

/**
 * This record represents our guest
 *
 * @param uniqueId    - the unique id of the guest
 * @param firstName   - the first name of the guest
 * @param lastName    - the last name of the guest
 * @param gender      - the gender of the guest
 * @param birthDate   - the date of birth of the guest
 * @param phoneNumber - the phone number of the guest
 * @param address     - the address of the guest
 * @param coronaState - the corona state of the guest
 *
 */

public record Guest(String uniqueId,
                    String firstName,
                    String lastName,
                    Gender gender,
                    String birthDate,
                    String phoneNumber,
                    Address address,
                    CoronaState coronaState) {
}
