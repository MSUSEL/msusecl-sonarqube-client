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
