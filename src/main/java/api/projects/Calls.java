package api.projects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static api.Constants.*;

/**
 * Hit SonarQube's api/projects/* endpoints
 */
public class Calls {
    private static final Logger logger = LoggerFactory.getLogger(Calls.class);

    private final HttpClient httpClient;
    private final String baseUrl;
    private final String token;
    private final Duration timeout = Duration.ofSeconds(30);


    public Calls(HttpClient httpClient, String baseUrl, String token) {
        this.httpClient = httpClient;
        this.baseUrl = baseUrl;
        this.token = token;
    }

    /**
     * deletes projects in bulk
     * @param projectKeys is a comma-separated list of project keys formatted as a single string
     */
    public void bulk_delete(String projectKeys) {
        HttpRequest.Builder requestBuilder = buildDefaultRequestTemplate();

        String formData = "projects=" + projectKeys;

        HttpRequest request = requestBuilder
                .uri(URI.create(baseUrl + API_PROJECTS_BULK_DELETE))
                .POST(HttpRequest.BodyPublishers.ofString(formData))
                .build();
    }

    /**
     * Creates a sonarQube project with the given project key and name
     * @param projectKey
     * @param projectName
     */
    public String create(String projectKey, String projectName) {
        HttpRequest.Builder requestBuilder = buildDefaultRequestTemplate();

        String formData = "project=" + projectKey + "&name=" + projectName;

        HttpRequest request = requestBuilder
                .uri(URI.create(baseUrl + API_PROJECTS_CREATE))
                .POST(HttpRequest.BodyPublishers.ofString(formData))
                .build();

        return sendRequest(request);
    }

    public String delete(String projectKey) {
        String formData = "project=" + projectKey;
        HttpRequest.Builder requestBuilder = buildDefaultRequestTemplate();

        HttpRequest request = requestBuilder
                .uri(URI.create(baseUrl + API_PROJECTS_BULK_DELETE))
                .POST(HttpRequest.BodyPublishers.ofString(formData))
                .build();

        return sendRequest(request);

    }

    public void export_findings() {}
    public void get_contains_ai_code() {}
    public void license_usage() {}
    public void search() {}
    public void set_contains_ai_code() {}
    public void update_key() {}
    public void update_visibility() {}

    private HttpRequest.Builder buildDefaultRequestTemplate() {
        return HttpRequest.newBuilder()
                .header(AUTH, BEARER + " " + token)
                .header(CONT_TYPE, APP_FORM)
                .timeout(timeout);
    }

    private String sendRequest(HttpRequest request) {
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();

        } catch (IOException | InterruptedException e) {
            logger.error(REQUEST_FAILED, e);
            throw new RuntimeException(e);
        }
    }

}
