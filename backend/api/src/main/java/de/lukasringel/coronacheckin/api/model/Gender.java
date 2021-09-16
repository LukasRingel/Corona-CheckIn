package de.lukasringel.coronacheckin.api.model;

/**
 * This enum provides all possible genders our guest can have
 *
 * @entry MALE    - our guest is a man
 * @entry FEMALE  - our guest is a woman
 * @entry UNKNOWN - the gender of the guest is unknown
 *
 */

public enum Gender {

    MALE,
    FEMALE,
    UNKNOWN;

    /**
     * This method searches for an enum entry with the provided ordinal
     *
     * @param ordinal - our target ordinal
     * @return        - the found entry or null
     */
    public static Gender byOrdinal(int ordinal) {
        return values()[ordinal];
    }

}
