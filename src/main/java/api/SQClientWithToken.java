package api;

import api.hotspots.HotspotsCalls;
import api.issues.IssuesCalls;
import api.projects.ProjectsCalls;

import java.net.http.HttpClient;

public class SQClientWithToken {
    private final HttpClient httpClient;
    private final String baseUrl;
    private final String token;

    private SQClientWithToken(HttpClient httpClient, String baseUrl, String token) {
        this.httpClient = httpClient;
        this.baseUrl = baseUrl;
        this.token = token;
    }

    public HotspotsCalls newHotspotsCalls() {
        return new HotspotsCalls(httpClient, baseUrl, token);
    }

    public IssuesCalls newIssuesCalls() {
        return new IssuesCalls(httpClient, baseUrl, token);
    }

    public ProjectsCalls newProjectsCalls() {
        return new ProjectsCalls(httpClient, baseUrl, token);
    }
}
