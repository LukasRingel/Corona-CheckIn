package de.lukasringel.coronacheckin.application.controller.implementation;

import de.lukasringel.coronacheckin.api.dto.EventInformationDTO;
import de.lukasringel.coronacheckin.api.dto.EventRegistrationDTO;
import de.lukasringel.coronacheckin.api.factory.EventFactory;
import de.lukasringel.coronacheckin.api.model.Event;
import de.lukasringel.coronacheckin.application.controller.rest.EventRestController;
import de.lukasringel.coronacheckin.application.database.repository.EventDataRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * This class implements our EventRestController
 */

@Slf4j
@Component
@AllArgsConstructor
public class EventRestControllerImplementation implements EventRestController {

    private final EventDataRepository eventDataRepository;

    /**
     * This post call registers a new event
     *
     * @param eventRegistrationDTO - our data from the frontend
     * @return - our ResponseEntity with the created Event
     */
    @Override
    public ResponseEntity<Event> registerEvent(EventRegistrationDTO eventRegistrationDTO) {
        Event event = EventFactory.fromDataTransferObject(eventRegistrationDTO);
        eventDataRepository.registerEvent(event);
        log.info("Created event with name " + event.name() + " got unique id " + event.uniqueId() + ".");
        return ResponseEntity.ok(event);
    }

    /**
     * This put call updates an event
     *
     * @param updatedEvent - the updated event
     * @return - our ResponseEntity with the updated Event
     */
    @Override
    public ResponseEntity<Event> updateEvent(Event updatedEvent) {
        eventDataRepository.updateEvent(updatedEvent);
        log.info("Updated event with name " + updatedEvent.name() + ".");
        return ResponseEntity.ok(updatedEvent);
    }

    /**
     * This get call provides the current event
     *
     * @return - our ResponseEntity with the current Event
     */
    @Override
    public ResponseEntity<EventInformationDTO> getCurrentEvent() {
        return ResponseEntity.ok(EventFactory.toEventInformationDTO(eventDataRepository.getCurrentEvent()));
    }

}
