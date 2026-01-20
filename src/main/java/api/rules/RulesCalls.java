package api.rules;

import api.BaseCalls;
import api.SimpleRequest;

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

    public HttpResponse<String> show(String ruleKey) {
        URI uri = URI.create(baseUrl + API_RULES_SHOW)
                .resolve("?" + RULES_PARAM + ruleKey);

        return simpleRequest.sendGetRequest(uri);
    }
}
