package api;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Serializer {
    private static final Logger logger = LoggerFactory.getLogger(Serializer.class);
    private final Gson gson = new Gson();

    public <T> String serialize(T businessObj) {
        try {
            return gson.toJson(businessObj);
        } catch (JsonSyntaxException e) {
            logger.error("Could not parse object to json.", e);
            throw new RuntimeException(e);
        }
    }

    public <T> T deserialize(String json, Class<T> clazz) {
        try {
            return gson.fromJson(json, clazz);
        } catch (JsonSyntaxException e) {
            logger.error("Could not parse json to object", e);
            throw new RuntimeException(e);
        }
    }
}
