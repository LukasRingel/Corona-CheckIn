package de.lukasringel.coronacheckin.application.controller.rest;

import de.lukasringel.coronacheckin.api.dto.GuestRegistrationDTO;
import de.lukasringel.coronacheckin.api.model.Event;
import de.lukasringel.coronacheckin.api.model.Guest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * This interface represents our guest rest controller
 */

@RestController
public interface GuestRestController {

    /**
     * This post call registers a new guest
     *
     * @param guestRegistrationDTO - our data from the frontend
     * @return - our ResponseEntity with the created Guest
     */
    @PostMapping("guest/register")
    ResponseEntity<Guest> registerGuest(@RequestBody GuestRegistrationDTO guestRegistrationDTO);

    /**
     * This get call provides all guests of an event
     *
     * @param event - the target event
     * @return - our ResponseEntity with our list
     */
    @GetMapping("guest/list")
    ResponseEntity<Collection<Guest>> getGuestsOfEvent(@RequestBody Event event);

}