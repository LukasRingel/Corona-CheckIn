package de.lukasringel.coronacheckin.api.model;

import java.util.Collection;

/**
 * This record represents an event like a party or a birthdate
 *
 * @param uniqueId       - the unique id of the event
 * @param name           - the name of the event
 * @param startTimeStamp - the start time of the event
 * @param guests         - all guests of the event
 * @param host           - the host of the event
 *
 */

public record Event(String uniqueId,
                    String name,
                    long startTimeStamp,
                    Collection<Guest> guests,
                    Guest host) {
}
