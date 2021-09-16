package de.lukasringel.coronacheckin.application.database.mongodb;

import com.mongodb.ConnectionString;
import com.mongodb.MongoCredential;

/**
 * This record contains all needed data to connect to our mongodb database
 *
 * @param hostname - the hostname of the database
 * @param port     - the port of the database
 * @param database - the name of the database
 * @param username - the username we should use
 * @param password - the password for the user
 */

public record MongoDBCredentials(String hostname,
                                 int port,
                                 String database,
                                 String username,
                                 String password) {

    /**
     * This method creates an instance of MongoCredential by our data
     *
     * @return - our MongoCredential instance
     */
    public MongoCredential buildMongoDBCredentials() {
        return MongoCredential.createCredential(
                username,
                database,
                password.toCharArray()
        );
    }

    /**
     * This method creates an instance of ConnectionString by our data
     *
     * @return - our ConnectionString instance
     */
    public ConnectionString buildMongoDBConnectionString(String connectionString) {
        return new ConnectionString(connectionString
                .replace("%hostname%", hostname)
                .replace("%port%", Integer.toString(port))
                .replace("%database%", database)
        );
    }

}
