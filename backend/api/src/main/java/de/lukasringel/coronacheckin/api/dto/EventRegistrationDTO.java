package de.lukasringel.coronacheckin.api.dto;

/**
 * This data transfer object contains our registration data from the frontend
 *
 * @param eventName       - the name of the event
 * @param hostName        - the first name of the host
 * @param hostLastName    - the last name of the host
 * @param hostGender      - the gender of the host
 * @param hostBirthDate   - the birthdate of the host
 * @param hostPhoneNumber - the phone number of the host
 * @param hostStreet      - the street of the host
 * @param hostPostCode    - the post code of the host
 * @param hostCity        - the city of the host
 * @param hostCorona      - the corona state of the host
 *
 */
public record EventRegistrationDTO(String eventName,
                                   String hostName,
                                   String hostLastName,
                                   int hostGender,
                                   String hostBirthDate,
                                   String hostPhoneNumber,
                                   String hostStreet,
                                   int hostPostCode,
                                   String hostCity,
                                   int hostCorona) {
}
