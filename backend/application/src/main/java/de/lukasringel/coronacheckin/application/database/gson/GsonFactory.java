package de.lukasringel.coronacheckin.application.database.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.TypeAdapterFactory;
import lombok.Builder;

/**
 * This factory provides methods to create gson instances
 */

public class GsonFactory {

    private static final TypeAdapterFactory RECORD_TYPE_ADAPTER_FACTORY = new RecordGsonTypeAdapter();

    /**
     * This method just builds a gson instance with our record type adapter factory
     *
     * @return -  our gson instance
     */
    public static Gson buildGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(RECORD_TYPE_ADAPTER_FACTORY);
        gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
        return gsonBuilder.create();
    }

}
