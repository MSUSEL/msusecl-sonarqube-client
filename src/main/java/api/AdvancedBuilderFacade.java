package api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.Map;

public class AdvancedBuilderFacade extends BaseCalls {
    private static final Logger logger = LoggerFactory.getLogger(AdvancedBuilderFacade.class);
    private String token = "";
    private final String baseUrl;
    private final HttpClient httpClient;
    private final AdvancedRequestBuilder builder;

    public AdvancedBuilderFacade(String baseUrl, HttpClient httpClient, String token) {
        super(httpClient, baseUrl, token);

        this.token = token;
        this.baseUrl = baseUrl;
        this.httpClient = httpClient;
        this.builder = new AdvancedRequestBuilder(httpClient, baseUrl, token);
    }

    public AdvancedBuilderFacade(String baseUrl, HttpClient httpClient) {
        super(httpClient, baseUrl);

        this.baseUrl = baseUrl;
        this.httpClient = httpClient;
        this.builder = new AdvancedRequestBuilder(httpClient, baseUrl);
    }

    /**
     * Execute a custom-built call
     * @param params Map of key, value pairs corresponding to SonarQube endpoint parameters
     *      documentation here: <a href="https://next.sonarqube.com/sonarqube/web_api/api/issues">...</a>
     * @return API Response
     */
    public HttpResponse<String> executeCall(Map<String, String> params, String endpoint) {
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.param(entry.getKey(), entry.getValue());
        }

        try {
            return builder.endpoint(baseUrl + endpoint).execute();
        } catch (Exception e) {
            logger.error("API request failed to send", e);
            throw new RuntimeException(e);
        }

    }
}
