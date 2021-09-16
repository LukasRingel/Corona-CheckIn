package de.lukasringel.coronacheckin.api.model;

/**
 * This record represents the address of a guest
 *
 * @param street   - the street and the house number of our guest
 * @param postCode - the unique postcode of the city of our guest
 * @param city     - the name of the city of our guest
 *
 */

public record Address(String street,
                      int postCode,
                      String city) {
}
