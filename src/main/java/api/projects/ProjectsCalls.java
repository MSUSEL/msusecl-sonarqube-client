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
import api.DataTransferWrapper;
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
     * POST
     * Deletes projects in bulk
     * @param projectKeys is a comma-separated list of project keys formatted as a single string
     */
    public int bulkDelete(String projectKeys) {
        String formData = PROJECT_PARAM + projectKeys;
        HttpResponse<String> response = simpleRequest.sendPostRequest(formData, API_PROJECTS_BULK_DELETE);

        return responseHandler.handleResponse(response);
    }

    /**
     * POST
     * Creates a sonarQube project with the given project key and name
     * @param projectKey
     * @param projectName
     */
    public DataTransferWrapper<ProjectResponse> create(String projectKey, String projectName) {
        String formData = PROJECT_PARAM + projectKey + "&" + NAME_PARAM + projectName;
        HttpResponse<String> response = simpleRequest.sendPostRequest(formData, API_PROJECTS_CREATE);

        return responseHandler.handleResponse(response, ProjectResponse.class);
    }

    /**
     * POST
     * Deletes a single, specified project
     * @param projectKey
     * @return API response
     */
    public int delete(String projectKey) {
        String formData = PROJECT_PARAM + projectKey;
        HttpResponse<String> response = simpleRequest.sendPostRequest(formData, API_PROJECTS_DELETE);

        return responseHandler.handleResponse(response);
    }

    /**
     * GET
     * Exports all findings of a specific project branch
     * @param projectKey
     * @return API response
     */
    public DataTransferWrapper<ExportFindingsResponse> exportFindings(String projectKey) {
        URI uri = URI.create(baseUrl + API_PROJECTS_EXPORT_FINDINGS)
                .resolve("?" + PROJECT_PARAM + URLEncoder.encode(projectKey, StandardCharsets.UTF_8));
        HttpResponse<String> response = simpleRequest.sendGetRequest(uri);

        return responseHandler.handleResponse(response, ExportFindingsResponse.class);
    }

    /**
     * GET
     * Gets a boolean value for whether the project contains AI code
     * @param projectKey
     * @return API response
     */
    public DataTransferWrapper<ContainsAiCode> getContainsAiCode(String projectKey) {
        URI uri = URI.create(baseUrl + API_PROJECTS_GET_CONTAINS_AI_CODE)
                .resolve("?" + PROJECT_PARAM + projectKey);

        HttpResponse<String> response = simpleRequest.sendGetRequest(uri);

        return responseHandler.handleResponse(response, ContainsAiCode.class);

    }

    /**
     * GET
     * Gets license usage metadata broken down per project
     * @return API response
     */
    public DataTransferWrapper<LicenseUsage> licenseUsage() {
        URI uri = URI.create(baseUrl + API_PROJECTS_LICENSE_USAGE);
        HttpResponse<String> response = simpleRequest.sendGetRequest(uri);

        return responseHandler.handleResponse(response, LicenseUsage.class);
    }

    /**
     * Gets matadata about existing sonarQube projects
     * @param projects comma-separated list of project keys formatted as a single String
     * @return API response
     */
    public DataTransferWrapper<Search> search(String projects) {
        URI uri = URI.create(baseUrl + API_PROJECTS_SEARCH)
                .resolve("?" + PROJECTS_PARAM + projects);

        HttpResponse<String> response = simpleRequest.sendGetRequest(uri);

        return responseHandler.handleResponse(response, Search.class);
    }

    /**
     * POST
     * Sets the contains ai code value in a project's metadata
     * @param projectKey
     * @param contains_ai_code true or false passed as a String
     * @return API response
     */
    public int setContainsAiCode(String projectKey, String contains_ai_code) {
        String formData = PROJECT_PARAM + projectKey + "&" + CONTAINS_AI_CODE_PARAM + contains_ai_code;
        HttpResponse<String> response = simpleRequest.sendPostRequest(formData, API_PROJECTS_SET_CONTAINS_AI_CODE);

        return responseHandler.handleResponse(response);
    }

    /**
     * POST
     * Update a project's subcomponent's keys
     * @param fromKey
     * @param toKey
     * @return API response
     */
    public int updateKey(String fromKey, String toKey) {
        String formData = FROM_PARAM + fromKey + "&" + TO_PARAM + toKey;
        HttpResponse<String> response = simpleRequest.sendPostRequest(formData, API_PROJECTS_UPDATE_KEY);

        return responseHandler.handleResponse(response);
    }

    /**
     * POST
     * Updates visibility of a project, application, or a portfolio
     * @param projectKey
     * @param visibility
     * @return API response
     */
    public int updateVisibility(String projectKey, String visibility) {
        String formData = PROJECT_PARAM + projectKey + "&" + VISIBILITY_PARAM + visibility;
        HttpResponse<String> response = simpleRequest.sendPostRequest(formData, API_PROJECTS_UPDATE_VISIBILITY);

        return responseHandler.handleResponse(response);
    }
}
