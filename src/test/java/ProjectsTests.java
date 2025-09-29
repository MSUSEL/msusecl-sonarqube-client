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
        System.out.println(response.body());
        assert response.statusCode() >= 200 && response.statusCode() < 300;
    }

    @Test
    public void testDelete() {
        HttpResponse<String> response = projectsCalls.delete(PROJECT_KEY);
        assert response.statusCode() >= 200 && response.statusCode() < 300;
    }
}
