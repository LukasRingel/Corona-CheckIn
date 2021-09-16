package de.lukasringel.coronacheckin.application.controller.implementation;

import de.lukasringel.coronacheckin.api.dto.GuestRegistrationDTO;
import de.lukasringel.coronacheckin.api.factory.GuestFactory;
import de.lukasringel.coronacheckin.api.model.Event;
import de.lukasringel.coronacheckin.api.model.Guest;
import de.lukasringel.coronacheckin.application.controller.rest.GuestRestController;
import de.lukasringel.coronacheckin.application.database.repository.EventDataRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * This class implements our GuestRestController
 */

@Slf4j
@Component
@AllArgsConstructor
public class GuestRestControllerImplementation implements GuestRestController {

    private final EventDataRepository eventDataRepository;

    /**
     * This post call registers a new guest
     *
     * @param guestRegistrationDTO - our data from the frontend
     * @return - our ResponseEntity with the created Guest
     */
    @Override
    public ResponseEntity<Guest> registerGuest(GuestRegistrationDTO guestRegistrationDTO) {
        Guest guest = GuestFactory.fromDataTransferObject(guestRegistrationDTO);
        eventDataRepository.registerGuest(guest);
        log.info("Registered guest " + guest.firstName() + " " + guest.lastName() + " for current event.");
        return ResponseEntity.ok(guest);
    }

    /**
     * This get call provides all guests of an event
     *
     * @param event - the target event
     * @return - our ResponseEntity with our list
     */
    @Override
    public ResponseEntity<Collection<Guest>> getGuestsOfEvent(Event event) {
        return ResponseEntity.ok(eventDataRepository.getCurrentEvent().guests());
    }

}
