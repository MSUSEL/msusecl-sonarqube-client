import api.projects.ProjectsCalls;
import org.junit.jupiter.api.Test;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class ProjectsTests {
    String serverURL = System.getenv("SONAR_QUBE_SERVER");
    String apiKey = System.getenv("SONAR_QUBE_KEY");
    ProjectsCalls projectsCalls = new ProjectsCalls(HttpClient.newHttpClient(), serverURL, apiKey);
    String PROJECT_KEY = "test_project";
    String PROJECT_NAME = "test_project";

    @Test
    public void testCreate() {
        HttpResponse<String> response = projectsCalls.create(PROJECT_KEY, PROJECT_NAME);
        assert response.statusCode() >= 200 && response.statusCode() < 300;
    }

    @Test
    public void testDelete() {
        HttpResponse<String> response = projectsCalls.delete(PROJECT_KEY);
        assert response.statusCode() >= 200 && response.statusCode() < 300;
    }
}
