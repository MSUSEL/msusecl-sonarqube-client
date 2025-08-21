package api.hotspots;

import api.AdvancedBuilderFacade;
import api.AdvancedRequestBuilder;
import api.BaseCalls;
import api.SimpleRequest;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.Map;

import static api.Constants.*;

/**
 * Hit SonarQube's api/hotspots/* endpoints
 */
public class HotspotsCalls extends BaseCalls {
    private final SimpleRequest simpleRequest;
    private final AdvancedBuilderFacade builderFacade;

    /**
     * For use with bearer token authentication
     * @param httpClient
     * @param baseUrl
     * @param token
     */
    public HotspotsCalls(HttpClient httpClient, String baseUrl, String token) {
        super(httpClient, baseUrl, token);

        this.simpleRequest = new SimpleRequest(httpClient, baseUrl, token, timeout);
        this.builderFacade = new AdvancedBuilderFacade(baseUrl, httpClient, token);
    }

    /**
     * For use with login/password based authentication
     * @param httpClient
     * @param baseUrl
     */
    public HotspotsCalls(HttpClient httpClient, String baseUrl) {
        super(httpClient, baseUrl);

        this.simpleRequest = new SimpleRequest(httpClient, baseUrl, timeout);
        this.builderFacade = new AdvancedBuilderFacade(baseUrl, httpClient);
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
        params.put("method", "POST");

        return builderFacade.executeCall(params, API_HOTSPOTS_SEARCH);
    }

    public HttpResponse<String> show(){}
}
