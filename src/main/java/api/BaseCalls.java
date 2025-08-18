package api;

import java.net.http.HttpClient;
import java.time.Duration;

public class BaseCalls {
    protected final HttpClient httpClient;
    protected final String baseUrl;
    protected String token = "";
    protected final Duration timeout = Duration.ofSeconds(30);

    public BaseCalls(HttpClient httpClient, String baseUrl, String token) {
        this.httpClient = httpClient;
        this.baseUrl = baseUrl;
        this.token = token;
    }

    public BaseCalls(HttpClient httpClient, String baseUrl) {
        this.httpClient = httpClient;
        this.baseUrl = baseUrl;
    }
}
