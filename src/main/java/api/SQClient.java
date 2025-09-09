package api;

import api.authentication.AuthenticationCalls;
import api.hotspots.HotspotsCalls;
import api.issues.IssuesCalls;
import api.projects.ProjectsCalls;

import java.net.http.HttpClient;

/**
 * Factory class allowing end user to instantiate whichever calls class(es) they require
 * All but the AuthenticationCalls, which deals only with login/password, have overloaded constructors
 * allowing the user to use either token-based or login/password-based authentication
 */
public class SQClient {

    public static ProjectsCalls newProjectsCalls(HttpClient httpClient, String baseUrl) {
        return new ProjectsCalls(httpClient, baseUrl);
    }

    public static AuthenticationCalls newAuthenticationCalls(HttpClient httpClient, String baseUrl) {
        return new AuthenticationCalls(httpClient, baseUrl);
    }

    public static HotspotsCalls newHotspotsCalls(HttpClient httpClient, String baseUrl, String token) {
        return new HotspotsCalls(httpClient, baseUrl, token);
    }

    public static HotspotsCalls newHotspotsCalls(HttpClient httpClient, String baseUrl) {
        return new HotspotsCalls(httpClient, baseUrl);
    }

    public static IssuesCalls newIssuesCalls(HttpClient httpClient, String baseUrl, String token) {
        return new IssuesCalls(httpClient, baseUrl, token);
    }

    public static IssuesCalls newIssuesCalls(HttpClient httpClient, String baseUrl) {
        return new IssuesCalls(httpClient, baseUrl);
    }
}
