package api;

import java.net.http.HttpResponse;

public interface IResponseHandler<T> {
    <U> DataTransferWrapper<U> handleResponse(HttpResponse<String> response, Class<U> clazz);
    int handleResponse(HttpResponse<String> response);
    boolean checkSuccess(HttpResponse<T> response);
    <U> String serialize(U businessObj);
    <U> U deserialize(String json, Class<U> clazz);
}
