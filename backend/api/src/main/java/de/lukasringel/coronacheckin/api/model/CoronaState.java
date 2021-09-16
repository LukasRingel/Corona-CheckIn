package de.lukasringel.coronacheckin.api.model;

/**
 * This enum provides all possible corona states of our guest
 *
 * @entry VACCINATED - The guest got both vaccinations
 * @entry RECOVERED  - The guest is recovered from his infection
 * @entry NOTHING    - Nothing of the upper states is fitting
 *
 */

public enum CoronaState {

    VACCINATED,
    RECOVERED,
    NOTHING;

    /**
     * This method searches for an enum entry with the provided ordinal
     *
     * @param ordinal - our target ordinal
     * @return        - the found entry or null
     */
    public static CoronaState byOrdinal(int ordinal) {
        return values()[ordinal];
    }

}
