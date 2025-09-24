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

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * Use this class to customize calls. Default calls elsewhere in this client use only
 * the required parameters. To customize those calls simply extend the relevant Calls class
 * and override the specific call method using this builder.
 */
public class AdvancedRequestBuilder {
    private final HttpClient httpClient;
    private final String baseUrl;
    private String endpoint;
    private final Map<String, String> queryParams = new HashMap<>();
    private final Map<String, String> headers = new HashMap<>();
    private String method = "GET";
    private String body;

    public AdvancedRequestBuilder(HttpClient client, String baseUrl, String token) {
        this.httpClient = client;
        this.baseUrl = baseUrl;

        // Set default headers
        this.headers.put("Authorization", "Bearer " + token);
        this.headers.put("Accept", "application/json");
    }

    /**
     * Handles the case that user has authenticated with login and password through the api
     * @param client
     * @param baseUrl
     */
    public AdvancedRequestBuilder(HttpClient client, String baseUrl) {
        this.httpClient = client;
        this.baseUrl = baseUrl;

        // Set default headers
        this.headers.put("Accept", "application/json");
    }

    public AdvancedRequestBuilder endpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    public AdvancedRequestBuilder param(String key, String value) {
        this.queryParams.put(key, value);
        return this;
    }

    public AdvancedRequestBuilder header(String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    public AdvancedRequestBuilder post(String body) {
        this.method = "POST";
        this.body = body;
        return this;
    }

    public HttpResponse<String> execute() throws Exception {
        String url = buildUrl();

        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(30));

        // Add headers
        headers.forEach(requestBuilder::header);

        // Set method and body
        if ("POST".equals(method) && body != null) {
            requestBuilder.POST(HttpRequest.BodyPublishers.ofString(body));
        } else {
            requestBuilder.GET();
        }

        HttpRequest request = requestBuilder.build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private String buildUrl() {
        StringBuilder url = new StringBuilder(baseUrl + endpoint);

        if (!queryParams.isEmpty()) {
            url.append("?");
            queryParams.entrySet().stream()
                    .map(entry -> entry.getKey() + "=" +
                            java.net.URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))
                    .forEach(param -> url.append(param).append("&"));
            // Remove trailing &
            url.setLength(url.length() - 1);
        }

        return url.toString();
    }
}