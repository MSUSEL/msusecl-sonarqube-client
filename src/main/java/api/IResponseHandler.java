package api;

import java.net.http.HttpResponse;

public interface IResponseHandler<T> {
    boolean checkSuccess(HttpResponse<T> response);
    <U> U handleResponseBody(HttpResponse<String> response, Class<U> clazz);
    <U> String serialize(U businessObj);
    <U> U deserialize(String json, Class<U> clazz);
}
