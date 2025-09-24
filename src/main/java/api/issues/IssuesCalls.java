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
package api.issues;

import api.AdvancedBuilderFacade;
import api.BaseCalls;
import api.SimpleRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import static api.Constants.API_ISSUES_SEARCH;

/**
 * Hits SonarQube api/issues/* endpoints
 * NOTE: currently only the search endpoint is implemented
 */
public class IssuesCalls extends BaseCalls {
    private static final Logger logger = LoggerFactory.getLogger(IssuesCalls.class);
    private final SimpleRequest simpleRequest;
    private final Map<String, String> parameters = new HashMap<>();
    private final AdvancedBuilderFacade builderFacade;

    /**
     * For use with bearer token based authentication
     * @param httpClient
     * @param baseUrl
     * @param token
     */
    public IssuesCalls(HttpClient httpClient, String baseUrl, String token) {
        super(httpClient, baseUrl, token);

        this.simpleRequest = new SimpleRequest(httpClient, baseUrl, token, timeout);
        this.builderFacade = new AdvancedBuilderFacade(httpClient, baseUrl, token);
    }

    /**
     * For use with login/password authentication
     * @param httpClient
     * @param baseUrl
     */
    public IssuesCalls(HttpClient httpClient, String baseUrl) {
        super(httpClient, baseUrl);

        this.simpleRequest = new SimpleRequest(httpClient, baseUrl, timeout);
        this.builderFacade = new AdvancedBuilderFacade(httpClient, baseUrl);
    }

    /**
     * Hits the api/issues/search endpoint
     * @param params Map of key, value pairs corresponding to SonarQube search parameters
     *      documentation here: <a href="https://next.sonarqube.com/sonarqube/web_api/api/issues">...</a>
     * @return API Response
     */
    public HttpResponse<String> search(Map<String, String> params) {
        return builderFacade.executeCall(params, API_ISSUES_SEARCH);
    }
}
