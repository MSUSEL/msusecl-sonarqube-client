package api;

import api.projects.ProjectsCalls;

import java.net.http.HttpClient;

public class SQClient {
    private SQClient(HttpClient httpClient, String baseUrl, String token) {}

    public static SQClient newSQClient(HttpClient httpClient, String baseUrl, String token) {
        return new SQClient(httpClient, baseUrl, token);
    }

    public ProjectsCalls projects(HttpClient httpClient, String baseUrl, String token) {
        return new ProjectsCalls(httpClient, baseUrl, token);
    }

    public
}
