package api.projects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
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
    public HttpResponse<String> bulk_delete(String projectKeys) {
        String formData = PROJECT_PARAM + projectKeys;

        return sendSimplePostRequest(formData, API_PROJECTS_BULK_DELETE);
    }

    /**
     * Creates a sonarQube project with the given project key and name
     * @param projectKey
     * @param projectName
     */
    public HttpResponse<String> create(String projectKey, String projectName) {
        String formData = PROJECT_PARAM + projectKey + "&" + NAME_PARAM + projectName;

        return sendSimplePostRequest(formData, API_PROJECTS_CREATE);
    }

    /**
     * Deletes a single, specified project
     * @param projectKey
     * @return API response
     */
    public HttpResponse<String> delete(String projectKey) {
        String formData = PROJECT_PARAM + projectKey;

        return sendSimplePostRequest(formData, API_PROJECTS_DELETE);
    }

    /**
     * Exports all findings of a specific project branch
     * @param projectKey
     * @return API response
     */
    public HttpResponse<String> export_findings(String projectKey) {
        URI uri = URI.create(baseUrl + API_PROJECTS_EXPORT_FINDINGS)
                .resolve("?" + PROJECT_PARAM + URLEncoder.encode(projectKey, StandardCharsets.UTF_8));

        return sendGetRequest(uri);
    }

    /**
     * Gets a boolean value for whether the project contains AI code
     * @param projectKey
     * @return API response
     */
    public HttpResponse<String> get_contains_ai_code(String projectKey) {
        URI uri = URI.create(baseUrl + API_PROJECTS_GET_CONTAINS_AI_CODE)
                .resolve("?" + PROJECT_PARAM + projectKey);

        return sendGetRequest(uri);
    }

    /**
     * Gets license usage metadata broken down per project
     * @return API response
     */
    public HttpResponse<String> license_usage() {
        URI uri = URI.create(baseUrl + API_PROJECTS_LICENSE_USAGE);

        return sendGetRequest(uri);
    }

    /**
     * Gets matadata about existing sonarQube projects
     * @param projects comma-separated list of project keys formatted as a single String
     * @return API response
     */
    public HttpResponse<String> search(String projects) {
        URI uri = URI.create(baseUrl + API_PROJECTS_SEARCH)
                .resolve("?" + PROJECTS_PARAM + projects);

        return sendGetRequest(uri);
    }

    /**
     * Sets the contains ai code value in a project's metadata
     * @param projectKey
     * @param contains_ai_code true or false passed as a String
     * @return API response
     */
    public HttpResponse<String> set_contains_ai_code(String projectKey, String contains_ai_code) {
        String formData = PROJECT_PARAM + projectKey + "&" + CONTAINS_AI_CODE_PARAM + contains_ai_code;

        return sendSimplePostRequest(formData, API_PROJECTS_SET_CONTAINS_AI_CODE);
    }

    public HttpResponse<String> update_key(String fromKey, String toKey) {
        String formData = FROM_PARAM + fromKey + "&" + TO_PARAM + toKey;

        return sendSimplePostRequest(formData, API_PROJECTS_UPDATE_KEY);
    }

    public HttpResponse<String> update_visibility(String projectKey, String visibility) {
        String formData = PROJECT_PARAM + projectKey + "&" + VISIBILITY_PARAM + visibility;

        return sendSimplePostRequest(formData, API_PROJECTS_UPDATE_VISIBILITY);
    }

    private HttpRequest.Builder buildDefaultRequestTemplate() {
        return HttpRequest.newBuilder()
                .header(AUTH, BEARER + " " + token)
                .header(CONT_TYPE, APP_FORM)
                .timeout(timeout);
    }

    private HttpResponse<String> sendGetRequest(URI uri) {
        HttpRequest.Builder requestBuilder = buildDefaultRequestTemplate();

        HttpRequest request = requestBuilder
                .uri(uri)
                .GET()
                .build();

        return sendRequest(request);
    }

    private HttpResponse<String> sendSimplePostRequest(String formData, String endpointPath) {
        HttpRequest.Builder requestBuilder = buildDefaultRequestTemplate();

        HttpRequest request = requestBuilder
                .uri(URI.create(baseUrl + endpointPath))
                .POST(HttpRequest.BodyPublishers.ofString(formData))
                .build();

        return sendRequest(request);
    }

    private HttpResponse<String> sendRequest(HttpRequest request) {
        try {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException | InterruptedException e) {
            logger.error(REQUEST_FAILED, e);
            throw new RuntimeException(e);
        }
    }


}
