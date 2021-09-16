package de.lukasringel.coronacheckin.application.controller.rest;

import de.lukasringel.coronacheckin.api.dto.EventInformationDTO;
import de.lukasringel.coronacheckin.api.dto.EventRegistrationDTO;
import de.lukasringel.coronacheckin.api.model.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This interface represents our event rest controller
 */

@CrossOrigin
@RestController
public interface EventRestController {

    /**
     * This post call registers a new event
     *
     * @param eventRegistrationDTO - our data from the frontend
     * @return - our ResponseEntity with the created Event
     */
    @PostMapping("event/register")
    ResponseEntity<Event> registerEvent(@RequestBody EventRegistrationDTO eventRegistrationDTO);

    /**
     * This put call updates an event
     *
     * @param updatedEvent - the updated event
     * @return - our ResponseEntity with the updated Event
     */
    @PutMapping("event/update")
    ResponseEntity<Event> updateEvent(@RequestBody Event updatedEvent);

    /**
     * This get call provides the current event
     *
     * @return - our ResponseEntity with the current Event
     */
    @GetMapping("event/current")
    ResponseEntity<EventInformationDTO> getCurrentEvent();

}