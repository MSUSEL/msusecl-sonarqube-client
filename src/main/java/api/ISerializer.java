package api;

public interface ISerializer {
    <T> String serialize(T businessObj);
    <T> T deserialize(String json, Class<T> clazz);
}
