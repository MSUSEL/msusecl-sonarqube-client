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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static api.Constants.*;
import static api.Constants.APP_FORM;

public class SimpleRequest {
    private static final Logger logger = LoggerFactory.getLogger(SimpleRequest.class);
    private final HttpClient httpClient;
    private final String baseUrl;
    private String token;
    private final Duration timeout;

    /**
     * For use with bearer token authentication
     * @param httpClient
     * @param baseUrl
     * @param token
     * @param timeout
     */
    public SimpleRequest(HttpClient httpClient, String baseUrl, String token, Duration timeout) {
        this.httpClient = httpClient;
        this.baseUrl = baseUrl;
        this.token = token;
        this.timeout = timeout;
    }

    /**
     * For use with login/password authentication
     * @param httpClient
     * @param baseUrl
     * @param timeout
     */
    public SimpleRequest(HttpClient httpClient, String baseUrl, Duration timeout) {
        this.httpClient = httpClient;
        this.baseUrl = baseUrl;
        this.timeout = timeout;
    }

    /**
     * Sends a body-less post request to a specified endpoint
     * @param endpointPath
     * @return API Response
     */
    public HttpResponse<String> sendPostRequest(String endpointPath) {
        HttpRequest.Builder requestBuilder = buildDefaultRequestTemplate(token, timeout);

        HttpRequest request = requestBuilder
                .uri(URI.create(baseUrl + endpointPath))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        return executeRequest(request);
    }

    /**
     * Sends a simple post request to a specified endpoint
     * @param formData formatted POST request parameters
     * @param endpointPath
     * @return API Response
     */
    public HttpResponse<String> sendPostRequest(String formData, String endpointPath) {
        HttpRequest.Builder requestBuilder = buildDefaultRequestTemplate(token, timeout);

        HttpRequest request = requestBuilder
                .uri(URI.create(baseUrl + endpointPath))
                .POST(HttpRequest.BodyPublishers.ofString(formData))
                .build();

        return executeRequest(request);
    }

    /**
     * Sends a simple GET request with specified URI
     * @param uri
     * @return API Response
     */
    public HttpResponse<String> sendGetRequest(URI uri) {
        HttpRequest.Builder requestBuilder = buildDefaultRequestTemplate(token, timeout);

        HttpRequest request = requestBuilder
                .uri(uri)
                .GET()
                .build();

        return executeRequest(request);
    }

    private HttpResponse<String> executeRequest(HttpRequest request) {
        try {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException | InterruptedException e) {
            logger.error(REQUEST_FAILED, e);
            throw new RuntimeException(e);
        }
    }

    private HttpRequest.Builder buildDefaultRequestTemplate(String token, Duration timeout) {
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .header(CONT_TYPE, APP_FORM)
                    .timeout(timeout);

            if (!this.token.isEmpty()) {
                requestBuilder.header(AUTH, BEARER + " " + token);
            }

            return requestBuilder;
    }
}
