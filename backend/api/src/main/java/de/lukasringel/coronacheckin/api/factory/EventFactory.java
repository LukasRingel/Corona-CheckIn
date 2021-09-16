package de.lukasringel.coronacheckin.api.factory;

import de.lukasringel.coronacheckin.api.dto.EventInformationDTO;
import de.lukasringel.coronacheckin.api.dto.EventRegistrationDTO;
import de.lukasringel.coronacheckin.api.model.Event;

import java.util.Collections;
import java.util.UUID;

/**
 * This factory provides methods to create Event instances
 */

public class EventFactory {

    /**
     * This method creates a new unique id by generating a new UUID
     *
     * @return - our new unique id
     */
    private static String generateUniqueId() {
        return UUID.randomUUID().toString();
    }

    /**
     * This method creates a new event instance by the provided data in the dto
     *
     * @param registrationDTO - our dto
     * @return - our created event
     */
    public static Event fromDataTransferObject(EventRegistrationDTO registrationDTO) {
        return new Event(generateUniqueId(),
                registrationDTO.eventName(),
                System.currentTimeMillis(),
                Collections.emptyList(),
                GuestFactory.withRawOrdinals(registrationDTO.hostName(),
                        registrationDTO.hostLastName(),
                        registrationDTO.hostGender(),
                        registrationDTO.hostBirthDate(),
                        registrationDTO.hostPhoneNumber(),
                        registrationDTO.hostStreet(),
                        registrationDTO.hostPostCode(),
                        registrationDTO.hostCity(),
                        registrationDTO.hostCorona()));
    }

    /**
     * This method creates a new EventInformationDTO instance by an event instance
     *
     * @param event - our event
     * @return - our dto instance
     */
    public static EventInformationDTO toEventInformationDTO(Event event) {
        return new EventInformationDTO(event.uniqueId(), event.name(), event.startTimeStamp());
    }

}