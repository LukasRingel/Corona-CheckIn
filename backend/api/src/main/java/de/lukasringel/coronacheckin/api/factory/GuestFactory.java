package de.lukasringel.coronacheckin.api.factory;

import de.lukasringel.coronacheckin.api.dto.GuestRegistrationDTO;
import de.lukasringel.coronacheckin.api.model.Address;
import de.lukasringel.coronacheckin.api.model.CoronaState;
import de.lukasringel.coronacheckin.api.model.Gender;
import de.lukasringel.coronacheckin.api.model.Guest;

import java.util.UUID;

/**
 * This factory provides methods to create Guest instances
 */

public class GuestFactory {

    /**
     * This method creates a new unique id by generating a new UUID
     *
     * @return - our new unique id
     */
    private static String generateUniqueId() {
        return UUID.randomUUID().toString();
    }

    /**
     * This method creates a new guest instance by a registration dto
     *
     * @param registrationDTO - our dto
     * @return - our new guest instance
     */
    public static Guest fromDataTransferObject(GuestRegistrationDTO registrationDTO) {
        return withRawOrdinals(registrationDTO.firstName(),
                registrationDTO.lastName(),
                registrationDTO.gender(),
                registrationDTO.birthDate(),
                registrationDTO.phoneNumber(),
                registrationDTO.street(),
                registrationDTO.postCode(),
                registrationDTO.city(),
                registrationDTO.coronaState());
    }

    /**
     * This method creates an instance of guest with the provided data, ordinals and raw address data
     *
     * @param firstName     - the first name of the guest
     * @param lastName      - the last name of the guest
     * @param genderOrdinal - the gender ordinal of the guest
     * @param birthDate     - the birthdate of the guest
     * @param phoneNumber   - the phone number of the guest
     * @param street        - the street of the guests address
     * @param postCode      - the postCode of the guests address
     * @param city          - the street of the guests address
     * @param coronaOrdinal - the corona state ordinal of the guest
     * @return - our new guest instance
     */
    public static Guest withRawOrdinals(String firstName, String lastName, int genderOrdinal,
                                        String birthDate, String phoneNumber, String street,
                                        int postCode, String city, int coronaOrdinal) {

        Address address = new Address(street, postCode, city);

        return new Guest(generateUniqueId(),
                firstName,
                lastName,
                Gender.byOrdinal(genderOrdinal),
                birthDate,
                phoneNumber,
                address,
                CoronaState.byOrdinal(coronaOrdinal)
        );

    }

}
