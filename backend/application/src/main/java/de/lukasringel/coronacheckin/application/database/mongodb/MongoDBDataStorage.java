package de.lukasringel.coronacheckin.application.database.mongodb;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import de.lukasringel.coronacheckin.application.database.gson.GsonFactory;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class provides many methods to work with our database connection
 */

@RequiredArgsConstructor
public class MongoDBDataStorage {

    private static final Gson GSON = GsonFactory.buildGson();

    private static final String CONNECTION_STRING = "mongodb://%hostname%:%port%/%database%?ssl=false";

    private final MongoDBCredentials credentials;

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;


    /**
     * This method is used to store data in our database
     *
     * @param storage  - the name of our storage
     * @param jsonData - our data as json string
     */
    public void storeData(String storage, String... jsonData) {
        if (jsonData.length == 1) {
            storeData(storage, Document.parse(jsonData[0]));
        } else {
            storeDataList(storage, Arrays.stream(jsonData).map(Document::parse).collect(Collectors.toList()));
        }
    }

    /**
     * This method is used to store data in our database
     *
     * @param storage  - the name of our storage
     * @param document - our data as document
     */
    public void storeData(String storage, Document... document) {
        if (document.length == 1) {
            mongoDatabase.getCollection(storage).insertOne(document[0]);
        } else {
            mongoDatabase.getCollection(storage).insertMany(Arrays.asList(document));
        }
    }

    /**
     * This method is used to store data in our database
     *
     * @param storage    - the name of our storage
     * @param jsonObject - our data as json object
     */
    public void storeData(String storage, JsonObject... jsonObject) {
        if (jsonObject.length == 1) {
            storeData(storage, Document.parse(jsonObject[0].toString()));
        } else {
            storeDataList(storage, Arrays.stream(jsonObject).map(object -> Document.parse(object.toString())).collect(Collectors.toList()));
        }
    }

    /**
     * This method is used to store data in our database
     *
     * @param storage            - the name of our storage
     * @param serializableObject - our data of any type
     */
    public <T> void storeData(String storage, T... serializableObject) {
        if (serializableObject.length == 1) {
            storeData(storage, Document.parse(GSON.toJson(serializableObject)));
        } else {
            storeDataList(storage, Arrays.stream(serializableObject).map(object -> Document.parse(GSON.toJson(object))).collect(Collectors.toList()));
        }
    }

    /**
     * This method is used to store data in our database
     *
     * @param storage      - the name of our storage
     * @param documentList - our data as document list
     */
    public <T> void storeDataList(String storage, List<T> documentList) {
        if (documentList.size() == 1) {
            storeData(storage, GSON.toJson(documentList.get(0)));
        } else {
            documentList.forEach(document -> storeData(storage, GSON.toJson(document)));
        }
    }

    /**
     * This method searches for all documents of the provided storage
     *
     * @param storage - our target storage
     * @return - our find iterable
     */
    public FindIterable<Document> getDataFromStorage(String storage) {
        return mongoDatabase.getCollection(storage).find();
    }

    /**
     * This method searches for documents of the provided storage by the provided filter
     *
     * @param storage - our target storage
     * @param bson    - our bson filter
     * @return - our find iterable
     */
    public FindIterable<Document> getDataFromStorage(String storage, Bson bson) {
        return mongoDatabase.getCollection(storage).find(bson);
    }

    /**
     * This method searches for generic data in our storage
     *
     * @param storage - the target storage
     * @param clazz   - the target clazz type
     * @return - our data as collection
     */
    public <T> Collection<T> getDataFromStorage(String storage, Class<T> clazz) {
        return mongoDatabase.getCollection(storage)
                .find()
                .into(new ArrayList<>())
                .stream()
                .map(document -> GSON.fromJson(document.toJson(), clazz))
                .collect(Collectors.toList());
    }

    /**
     * This method searches for generic data in our storage by the provided filter
     *
     * @param storage - the target storage
     * @param clazz   - the target clazz type
     * @param bson    - our bson filter
     * @return - our data as collection
     */
    public <T> Collection<T> getDataFromStorage(String storage, Class<T> clazz, Bson bson) {
        return mongoDatabase.getCollection(storage)
                .find(bson)
                .into(new ArrayList<>())
                .stream()
                .map(document -> GSON.fromJson(document.toJson(), clazz))
                .collect(Collectors.toList());
    }

    /**
     * This method searches for a single document of the provided type in the
     * provided storage with the provided bson filter
     *
     * @param storage - our target storage
     * @param clazz   - our target clazz type
     * @param bson    - our bson filter
     * @return - our data as instance of clazz
     */
    public <T> T getSingleDataFromStorage(String storage, Class<T> clazz, Bson bson) {
        return getSingleDataFromStorage(storage, clazz, bson, null);
    }

    /**
     * This method searches for a single document of the provided type in the
     * provided storage with the provided bson filter and returns the default
     * value, if no document is present
     *
     * @param storage      - our target storage
     * @param clazz        - our target clazz type
     * @param bson         - our bson filter
     * @param defaultValue - our default value
     * @return - our data as instance of clazz
     */
    public <T> T getSingleDataFromStorage(String storage, Class<T> clazz, Bson bson, T defaultValue) {
        Document document = getDataFromStorage(storage, bson).first();
        return document != null ? GSON.fromJson(document.toJson(), clazz) : defaultValue;
    }

    /**
     * This method deletes one document from the provided storage by the provided filter
     *
     * @param storage - our target storage
     * @param bson    - our bson filter
     */
    public void deleteSingleData(String storage, Bson bson) {
        mongoDatabase.getCollection(storage).deleteOne(bson);
    }

    /**
     * This method deletes many documents from the provided storage by the provided filter
     *
     * @param storage - our target storage
     * @param bson    - our bson filter
     */
    public void deleteMultipleData(String storage, Bson bson) {
        mongoDatabase.getCollection(storage).deleteMany(bson);
    }

    /**
     * This method replaces some data in our database by the provided filter with the provided data
     *
     * @param storage  - our target storage
     * @param bson     - our bson filter
     * @param jsonData - our new data
     */
    public void replaceData(String storage, Bson bson, String jsonData) {
        replaceData(storage, bson, Document.parse(jsonData));
    }

    /**
     * This method replaces some data in our database by the provided filter with the provided data
     *
     * @param storage  - our target storage
     * @param bson     - our bson filter
     * @param document - our new data
     */
    public void replaceData(String storage, Bson bson, Document document) {
        mongoDatabase.getCollection(storage).replaceOne(bson, document);
    }

    /**
     * This method replaces some data in our database by the provided filter with the provided data
     *
     * @param storage    - our target storage
     * @param bson       - our bson filter
     * @param jsonObject - our new data
     */
    public void replaceData(String storage, Bson bson, JsonObject jsonObject) {
        replaceData(storage, bson, Document.parse(jsonObject.toString()));
    }

    /**
     * This method replaces some data in our database by the provided filter with the provided data
     *
     * @param storage            - our target storage
     * @param bson               - our bson filter
     * @param serializableObject - our new data
     */
    public void replaceData(String storage, Bson bson, Object serializableObject) {
        replaceData(storage, bson, Document.parse(GSON.toJson(serializableObject)));
    }

    /**
     * This method establishes a new connection to our database
     */
    public void connectToStorage() {
        mongoClient = MongoClients.create(MongoClientSettings.builder()
                .applyConnectionString(credentials.buildMongoDBConnectionString(CONNECTION_STRING))
                .credential(credentials.buildMongoDBCredentials())
                .build());
        mongoDatabase = mongoClient.getDatabase(credentials.database());
    }

    /**
     * This method closes our current database connection
     */
    public void disconnectFromStorage() {
        mongoClient.close();
    }


}