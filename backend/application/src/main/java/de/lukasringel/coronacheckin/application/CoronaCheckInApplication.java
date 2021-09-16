package de.lukasringel.coronacheckin.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = {
        MongoAutoConfiguration.class,
        MongoDataAutoConfiguration.class
})
public class CoronaCheckInApplication {

    /**
     * Main entry point of the application which bootstraps our spring application
     *
     * @param args - provided startup parameters
     */
    public static void main(String[] args) {
        SpringApplication.run(CoronaCheckInApplication.class, args);
    }

}
