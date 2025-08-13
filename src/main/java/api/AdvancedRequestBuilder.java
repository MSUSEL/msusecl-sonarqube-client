package api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class AdvancedRequestBuilder {
    private final HttpClient httpClient;
    private final String baseUrl;
    private final String token;
    private String endpoint;
    private final Map<String, String> queryParams = new HashMap<>();
    private final Map<String, String> headers = new HashMap<>();
    private String method = "GET";
    private String body;

    public AdvancedRequestBuilder(HttpClient client, String baseUrl, String token) {
        this.httpClient = client;
        this.baseUrl = baseUrl;
        this.token = token;
        // Set default headers
        this.headers.put("Authorization", "Bearer " + token);
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