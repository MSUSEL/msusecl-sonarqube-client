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
package api.hotspots;

import api.AdvancedBuilderFacade;
import api.BaseCalls;
import api.SimpleRequest;
import api.hotspots.responseObjects.Search;
import api.hotspots.responseObjects.Show;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.Map;

import static api.Constants.*;

/**
 * Hit SonarQube's api/hotspots/* endpoints
 */
public class HotspotsCalls extends BaseCalls {
    private static final Logger LOGGER = LoggerFactory.getLogger(HotspotsCalls.class);
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
        this.builderFacade = new AdvancedBuilderFacade(httpClient, baseUrl, token);
    }

    /**
     * For use with login/password based authentication
     * @param httpClient
     * @param baseUrl
     */
    public HotspotsCalls(HttpClient httpClient, String baseUrl) {
        super(httpClient, baseUrl);

        this.simpleRequest = new SimpleRequest(httpClient, baseUrl, timeout);
        this.builderFacade = new AdvancedBuilderFacade(httpClient, baseUrl);
    }

    /**
     * Change the status of a Security Hotspot
     * Requires the 'Administer Security Hotspot' permission
     * @param hotspotKey
     * @param newStatus
     * @return API response
     */
    public boolean changeStatus(String hotspotKey, String newStatus) {
        String formData = STATUS_PARAM + newStatus;

        HttpResponse<String> response = simpleRequest.sendPostRequest(formData, API_HOTSPOTS_CHANGE_STATUS);
        logResponseStatusCode(response);

        return responseHandler.checkSuccess(response);
    }

    /**
     * Search for Security Hotspots
     * @param params a map of search parameters and their values
     *               Documentation: <a href="https://next.sonarqube.com/sonarqube/web_api/api/hotspots">...</a>
     * @return API response
     */
    public Search search(Map<String, String> params) {
        params.put("method", "POST");

        HttpResponse<String> response = builderFacade.executeCall(params, API_HOTSPOTS_SEARCH);
        logResponseStatusCode(response);

        return responseHandler.deserialize(response.body(), Search.class);
    }

    /**
     * Provides the details of a Security Hotspot
     * @param hotSpotKey
     * @return API Response
     */
    public Show show(String hotSpotKey) {
        URI uri = URI.create(baseUrl + API_HOTSPOTS_SHOW)
                .resolve("?" + HOTSPOT_PARAM + hotSpotKey);

        HttpResponse<String> response = simpleRequest.sendGetRequest(uri);
        logResponseStatusCode(response);

       return responseHandler.deserialize(response.body(), Show.class);
    }
}
