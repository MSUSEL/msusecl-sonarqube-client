package api.rules;

import api.BaseCalls;
import api.SimpleRequest;
import api.rules.responseObjects.Show;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import static api.Constants.API_RULES_SHOW;
import static api.Constants.RULES_PARAM;

public class RulesCalls extends BaseCalls {
    private final SimpleRequest simpleRequest;

    public RulesCalls(HttpClient httpClient, String baseUrl, String token) {
        super(httpClient, baseUrl, token);

        this.simpleRequest = new SimpleRequest(httpClient, baseUrl, token, timeout);
    }

    public RulesCalls(HttpClient httpClient, String baseUrl) {
        super(httpClient, baseUrl);

        this.simpleRequest = new SimpleRequest(httpClient, baseUrl, timeout);
    }

    public Show show(String ruleKey) {
        URI uri = URI.create(baseUrl + API_RULES_SHOW + "?" + RULES_PARAM + ruleKey);
        HttpResponse<String> response = simpleRequest.sendGetRequest(uri);
        logResponseStatusCode(response);

        return responseHandler.deserialize(response.body(), Show.class);
    }
}
