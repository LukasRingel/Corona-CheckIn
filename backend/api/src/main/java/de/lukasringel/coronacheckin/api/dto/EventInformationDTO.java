package de.lukasringel.coronacheckin.api.dto;

/**
 * This  data transfer object contains information about the event
 * We use this dto for our frontend so nobody can view personal information
 * if he isn't authorised
 *
 * @param uniqueId       - the unique id of the event
 * @param name           - the name of the event
 * @param startTimeStamp - the start timestamp of the event
 *
 */

public record EventInformationDTO(String uniqueId,
                                  String name,
                                  long startTimeStamp) {
}
