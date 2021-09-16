package de.lukasringel.coronacheckin.application.database.repository;

import com.mongodb.client.model.Filters;
import de.lukasringel.coronacheckin.api.model.Event;
import de.lukasringel.coronacheckin.api.model.Guest;
import de.lukasringel.coronacheckin.application.database.mongodb.MongoDBDataStorage;
import org.springframework.stereotype.Component;

import java.util.Comparator;

/**
 * This component communicates with our database and provides the data
 */

@Component
public class EventDataRepository extends DataRepository<Event> {

    public EventDataRepository(MongoDBDataStorage mongoDBDataStorage) {
        super("events", Event.class, mongoDBDataStorage);
    }

    /**
     * This method inserts our event into the database
     *
     * @param event - the target event
     */
    public void registerEvent(Event event) {
        storeData(event);
    }

    /**
     * This method updates our event in our database
     *
     * @param event - the updated event
     */
    public void updateEvent(Event event) {
        replaceData(Filters.eq("uniqueId", event.uniqueId()), event);
    }

    /**
     * This method searches in our database for the last created event
     *
     * @return -  our current event
     */
    public Event getCurrentEvent() {
        return getDataFromStorage().stream().max(Comparator.comparingLong(Event::startTimeStamp)).orElse(null);
    }

    /**
     * This method adds a guest to our current event and updates the database
     *
     * @param guest - our guest
     */
    public void registerGuest(Guest guest) {
        Event currentEvent = getCurrentEvent();
        currentEvent.guests().add(guest);
        updateEvent(currentEvent);
    }

}
