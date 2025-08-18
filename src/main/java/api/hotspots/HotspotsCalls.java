package api.hotspots;

import api.BaseCalls;
import api.SimpleRequest;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;

/**
 * Hit SonarQube's api/hotspots/* endpoints
 */
public class HotspotsCalls extends BaseCalls {
    private final SimpleRequest simpleRequest;

    /**
     * For use with bearer token authentication
     * @param httpClient
     * @param baseUrl
     * @param token
     */
    public HotspotsCalls(HttpClient httpClient, String baseUrl, String token) {
        super(httpClient, baseUrl, token);

        this.simpleRequest = new SimpleRequest(httpClient, baseUrl, token, timeout);
    }

    /**
     * For use with login/password based authentication
     * @param httpClient
     * @param baseUrl
     */
    public HotspotsCalls(HttpClient httpClient, String baseUrl) {
        super(httpClient, baseUrl);

        this.simpleRequest = new SimpleRequest(httpClient, baseUrl, timeout);
    }

    public HttpResponse<String> changeStatus(String hotspotKey, String newStatus) {}
    public HttpResponse<String> search() {}
    public HttpResponse<String> show(){}
}
