/*
 * MIT License
 *
 * Copyright (c) 2024 Montana State University Software Engineering and Cybersecurity Laboratory
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package api;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpResponse;

import static api.Constants.BAD_STATUS_MESSAGE;
import static api.Constants.GOOD_STATUS_MESSAGE;

public class ResponseHandler implements IResponseHandler<String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseHandler.class);
    private final Gson gson = new Gson();

    @Override
    public <U> DataTransferWrapper<U> handleResponse(HttpResponse<String> response, Class<U> clazz) {
        U responseObject = null;

        if (checkSuccess(response)) {
            responseObject = deserialize(response.body(), clazz);
        }

        return new DataTransferWrapper<>(response.statusCode(), responseObject);
    }

    @Override
    public int handleResponse(HttpResponse<String> response) {
        checkSuccess(response);

        return response.statusCode();
    }

    @Override
    public boolean checkSuccess(HttpResponse<String> response) {
        int statusCode = response.statusCode();
        boolean success = statusCode >= 200 && statusCode < 300;

        if (success) {
            LOGGER.info(GOOD_STATUS_MESSAGE, statusCode);
        } else {
            LOGGER.error(BAD_STATUS_MESSAGE, statusCode);
        }

        return success;
    }

    @Override
    public <U> String serialize(U businessObj) {
        try {
            return gson.toJson(businessObj);
        } catch (JsonSyntaxException e) {
            LOGGER.error("Could not parse object to json.", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public <U> U deserialize(String json, Class<U> clazz) {
        try {
            return gson.fromJson(json, clazz);
        } catch (JsonSyntaxException e) {
            LOGGER.error("Could not parse json to object", e);
            throw new RuntimeException(e);
        }
    }
}
