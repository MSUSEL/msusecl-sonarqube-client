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
package api.projects;

import api.BaseCalls;
import api.SimpleRequest;
import api.projects.responseObjects.*;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import static api.Constants.*;

/**
 * Hit SonarQube's api/projects/* endpoints
 */
public class ProjectsCalls extends BaseCalls {
    private final SimpleRequest simpleRequest;

    public ProjectsCalls(HttpClient httpClient, String baseUrl, String token) {
        super(httpClient, baseUrl, token);

        this.simpleRequest = new SimpleRequest(httpClient, baseUrl, token, timeout);
    }

    public ProjectsCalls(HttpClient httpClient, String baseUrl) {
        super(httpClient, baseUrl);

        this.simpleRequest = new SimpleRequest(httpClient, baseUrl, timeout);
    }

    /**
     * Deletes projects in bulk
     * @param projectKeys is a comma-separated list of project keys formatted as a single string
     */
    public boolean bulkDelete(String projectKeys) {
        String formData = PROJECT_PARAM + projectKeys;
        HttpResponse<String> response = simpleRequest.sendPostRequest(formData, API_PROJECTS_BULK_DELETE);
        logResponseStatusCode(response);

        return responseHandler.checkSuccess(response);
    }

    /**
     * Creates a sonarQube project with the given project key and name
     * @param projectKey
     * @param projectName
     */
    public ProjectResponse create(String projectKey, String projectName) {
        String formData = PROJECT_PARAM + projectKey + "&" + NAME_PARAM + projectName;
        HttpResponse<String> response = simpleRequest.sendPostRequest(formData, API_PROJECTS_CREATE);
        logResponseStatusCode(response);

        ProjectResponse handledResponse = responseHandler.deserialize(response.body(), ProjectResponse.class);

        return handledResponse;
    }

    /**
     * Deletes a single, specified project
     * @param projectKey
     * @return API response
     */
    public boolean delete(String projectKey) {
        String formData = PROJECT_PARAM + projectKey;
        HttpResponse<String> response = simpleRequest.sendPostRequest(formData, API_PROJECTS_DELETE);
        logResponseStatusCode(response);

        return responseHandler.checkSuccess(response);
    }

    /**
     * Exports all findings of a specific project branch
     * @param projectKey
     * @return API response
     */
    public ExportFindingsResponse exportFindings(String projectKey) {
        URI uri = URI.create(baseUrl + API_PROJECTS_EXPORT_FINDINGS)
                .resolve("?" + PROJECT_PARAM + URLEncoder.encode(projectKey, StandardCharsets.UTF_8));
        HttpResponse<String> response = simpleRequest.sendGetRequest(uri);
        logResponseStatusCode(response);

        return responseHandler.deserialize(response.body(), ExportFindingsResponse.class);
    }

    /**
     * Gets a boolean value for whether the project contains AI code
     * @param projectKey
     * @return API response
     */
    public ContainsAiCode getContainsAiCode(String projectKey) {
        URI uri = URI.create(baseUrl + API_PROJECTS_GET_CONTAINS_AI_CODE)
                .resolve("?" + PROJECT_PARAM + projectKey);

        HttpResponse<String> response = simpleRequest.sendGetRequest(uri);
        logResponseStatusCode(response);

        return responseHandler.deserialize(response.body(), ContainsAiCode.class);
    }

    /**
     * Gets license usage metadata broken down per project
     * @return API response
     */
    public LicenseUsage licenseUsage() {
        URI uri = URI.create(baseUrl + API_PROJECTS_LICENSE_USAGE);
        HttpResponse<String> response = simpleRequest.sendGetRequest(uri);
        logResponseStatusCode(response);

        return responseHandler.deserialize(response.body(), LicenseUsage.class);
    }

    /**
     * Gets matadata about existing sonarQube projects
     * @param projects comma-separated list of project keys formatted as a single String
     * @return API response
     */
    public Search search(String projects) {
        URI uri = URI.create(baseUrl + API_PROJECTS_SEARCH)
                .resolve("?" + PROJECTS_PARAM + projects);

        HttpResponse<String> response = simpleRequest.sendGetRequest(uri);
        logResponseStatusCode(response);

        return responseHandler.deserialize(response.body(), Search.class);
    }

    /**
     * Sets the contains ai code value in a project's metadata
     * @param projectKey
     * @param contains_ai_code true or false passed as a String
     * @return API response
     */
    public boolean setContainsAiCode(String projectKey, String contains_ai_code) {
        String formData = PROJECT_PARAM + projectKey + "&" + CONTAINS_AI_CODE_PARAM + contains_ai_code;
        HttpResponse<String> response = simpleRequest.sendPostRequest(formData, API_PROJECTS_SET_CONTAINS_AI_CODE);
        logResponseStatusCode(response);

        return responseHandler.checkSuccess(response);
    }

    /**
     * Update a project's subcomponent's keys
     * @param fromKey
     * @param toKey
     * @return API response
     */
    public boolean updateKey(String fromKey, String toKey) {
        String formData = FROM_PARAM + fromKey + "&" + TO_PARAM + toKey;
        HttpResponse<String> response = simpleRequest.sendPostRequest(formData, API_PROJECTS_UPDATE_KEY);

        return responseHandler.checkSuccess(response);
    }

    /**
     * Updates visibility of a project, application, or a portfolio
     * @param projectKey
     * @param visibility
     * @return API response
     */
    public HttpResponse<String> updateVisibility(String projectKey, String visibility) {
        String formData = PROJECT_PARAM + projectKey + "&" + VISIBILITY_PARAM + visibility;

        return simpleRequest.sendPostRequest(formData, API_PROJECTS_UPDATE_VISIBILITY);
    }
}
