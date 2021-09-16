package de.lukasringel.coronacheckin.application.database.repository;

import com.google.gson.Gson;
import de.lukasringel.coronacheckin.application.database.gson.GsonFactory;
import de.lukasringel.coronacheckin.application.database.mongodb.MongoDBDataStorage;
import lombok.RequiredArgsConstructor;
import org.bson.conversions.Bson;

import java.util.Collection;

/**
 * This abstract class is a wrapper for our MongoDBDataStorage and provides better methods
 * to store/read/replace/delete data of a certain type T
 *
 * @param <T> -  our target type
 */

@RequiredArgsConstructor
public abstract class DataRepository<T> {

    private static final Gson GSON = GsonFactory.buildGson();

    private final String storage;
    private final Class<T> clazz;
    private final MongoDBDataStorage mongoDBDataStorage;

    /**
     * This method is just a wrapper for the dataStorage
     *
     * @param object - the object we want to store
     */
    public void storeData(T[] object) {
        mongoDBDataStorage.storeData(storage, object);
    }

    /**
     * This method is just a wrapper for the dataStorage
     *
     * @param object - the object we want to store
     */
    public void storeData(T object) {
        mongoDBDataStorage.storeData(storage, toJson(object));
    }

    /**
     * This method is just a wrapper for the dataStorage
     *
     * @param bson - our bson filter
     */
    public T getSingleDataFromStorage(Bson bson) {
        return getSingleDataFromStorage(bson, null);
    }

    /**
     * This method is just a wrapper for the dataStorage
     *
     * @param bson         - our bson filter
     * @param defaultValue - our default value
     */
    public T getSingleDataFromStorage(Bson bson, T defaultValue) {
        return mongoDBDataStorage.getSingleDataFromStorage(storage, clazz, bson, defaultValue);
    }

    /**
     * This method is just a wrapper for the dataStorage
     */
    public Collection<T> getDataFromStorage() {
        return mongoDBDataStorage.getDataFromStorage(storage, clazz);
    }

    /**
     * This method is just a wrapper for the dataStorage
     *
     * @param bson - our bson filter
     */
    public Collection<T> getDataFromStorage(Bson bson) {
        return mongoDBDataStorage.getDataFromStorage(storage, clazz, bson);
    }

    /**
     * This method is just a wrapper for the dataStorage
     *
     * @param bson - our bson filter
     */
    public void deleteSingleData(Bson bson) {
        mongoDBDataStorage.deleteSingleData(storage, bson);
    }

    /**
     * This method is just a wrapper for the dataStorage
     *
     * @param bson - our bson filter
     */
    public void deleteMultipleData(Bson bson) {
        mongoDBDataStorage.deleteMultipleData(storage, bson);
    }

    /**
     * This method is just a wrapper for the dataStorage
     *
     * @param bson   - our bson filter
     * @param object - our new data
     */
    public void replaceData(Bson bson, T object) {
        mongoDBDataStorage.replaceData(storage, bson, toJson(object));
    }

    /**
     * This method creates a json string from any object or record
     *
     * @param object - our object we want to stringify
     * @return - our json string
     */
    private String toJson(T object) {
        return GSON.toJson(object);
    }

    /**
     * This method creates an object of our type T from a json string
     *
     * @param json - our json string
     * @return - our T object
     */
    private T fromJson(String json) {
        return GSON.fromJson(json, clazz);
    }

}
