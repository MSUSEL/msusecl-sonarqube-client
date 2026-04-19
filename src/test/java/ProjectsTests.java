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
import api.issues.IssuesCalls;
import api.issues.responseObjects.Search;
import api.projects.ProjectsCalls;
import api.projects.responseObjects.ProjectResponse;
import org.junit.jupiter.api.*;

import java.net.http.HttpClient;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProjectsTests {
    String serverURL = System.getenv("SONAR_QUBE_SERVER");
    String apiKey = System.getenv("SONAR_QUBE_TOKEN");
    ProjectsCalls projectsCalls = new ProjectsCalls(HttpClient.newHttpClient(), serverURL, apiKey);
    IssuesCalls issuesCalls = new IssuesCalls(HttpClient.newHttpClient(), System.getenv("SONAR_QUBE_SERVER"), System.getenv("SONAR_QUBE_TOKEN"));
    String PROJECT_KEY = "test_project_" + System.currentTimeMillis();
    String PROJECT_NAME = "test_project";

    @AfterEach
    public void cleanup() {
        try {
            projectsCalls.delete(PROJECT_KEY);
        } catch (Exception e) {
            // If project does not exist, ignore
        }
    }

    @Test
    @Order(1)
    public void testCreate() {
        ProjectResponse response = projectsCalls.create(PROJECT_KEY, PROJECT_NAME);

        assertEquals(PROJECT_KEY, response.getProject().getKey());
        assertEquals(PROJECT_NAME, response.getProject().getName());
    }

    @Test
    @Order(2)
    public void testDelete() {
        projectsCalls.create(PROJECT_KEY, PROJECT_NAME);
        
        boolean response = projectsCalls.delete(PROJECT_KEY);
        assertTrue(response);
    }
}
