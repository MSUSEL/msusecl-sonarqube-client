package api.hotspots;

import api.AdvancedRequestBuilder;
import api.BaseCalls;
import api.SimpleRequest;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.Map;

import static api.Constants.API_HOTSPOTS_CHANGE_STATUS;
import static api.Constants.STATUS_PARAM;

/**
 * Hit SonarQube's api/hotspots/* endpoints
 */
public class HotspotsCalls extends BaseCalls {
    private final SimpleRequest simpleRequest;
    private final AdvancedRequestBuilder builder;

    /**
     * For use with bearer token authentication
     * @param httpClient
     * @param baseUrl
     * @param token
     */
    public HotspotsCalls(HttpClient httpClient, String baseUrl, String token) {
        super(httpClient, baseUrl, token);

        this.simpleRequest = new SimpleRequest(httpClient, baseUrl, token, timeout);
        this.builder = new AdvancedRequestBuilder(httpClient, baseUrl, token);
    }

    /**
     * For use with login/password based authentication
     * @param httpClient
     * @param baseUrl
     */
    public HotspotsCalls(HttpClient httpClient, String baseUrl) {
        super(httpClient, baseUrl);

        this.simpleRequest = new SimpleRequest(httpClient, baseUrl, timeout);
        this.builder = new AdvancedRequestBuilder(httpClient, baseUrl);
    }

    /**
     * Change the status of a Security Hotspot
     * Requires the 'Administer Security Hotspot' permission
     * @param hotspotKey
     * @param newStatus
     * @return API response
     */
    public HttpResponse<String> changeStatus(String hotspotKey, String newStatus) {
        String formData = STATUS_PARAM + newStatus;

        return simpleRequest.sendPostRequest(formData, API_HOTSPOTS_CHANGE_STATUS);
    }

    public HttpResponse<String> search(Map<String, String> params) {
        for
    }
    public HttpResponse<String> show(){}
}
