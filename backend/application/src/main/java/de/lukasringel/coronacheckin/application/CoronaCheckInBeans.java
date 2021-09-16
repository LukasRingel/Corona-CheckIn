package de.lukasringel.coronacheckin.application;

import de.lukasringel.coronacheckin.application.database.mongodb.MongoDBCredentials;
import de.lukasringel.coronacheckin.application.database.mongodb.MongoDBDataStorage;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.util.Properties;

@Component
public class CoronaCheckInBeans {

    private final Properties dataSourceProperties = new Properties();

    /**
     * This method gets called after class construction
     * and loads our properties from the datasource file
     */
    @SneakyThrows
    @PostConstruct
    private void loadProperties() {
        dataSourceProperties.load(new FileInputStream(ResourceUtils.getFile("./config/server/datasource.properties")));
    }

    /**
     * This bean provides an instance of a DataStorageProvider with our
     * credentials we provide in datasource.properties
     *
     * @return - DataStorageProvider instance
     */
    @Bean
    public MongoDBDataStorage dataStorageProvider() {
        MongoDBDataStorage storageProvider = new MongoDBDataStorage(new MongoDBCredentials(
                dataSourceProperties.getProperty("mongodb.hostname"),
                Integer.parseInt(dataSourceProperties.getProperty("mongodb.port")),
                dataSourceProperties.getProperty("mongodb.database"),
                dataSourceProperties.getProperty("mongodb.username"),
                dataSourceProperties.getProperty("mongodb.password"))
        );
        storageProvider.connectToStorage();
        return storageProvider;
    }

}
