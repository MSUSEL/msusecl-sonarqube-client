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
package api;

import api.authentication.AuthenticationCalls;
import api.hotspots.HotspotsCalls;
import api.issues.IssuesCalls;
import api.projects.ProjectsCalls;

import java.net.http.HttpClient;

public class SQClient {
    //TODO: Convert to interface so consuming programs can mock this one more easily
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final String baseUrl;

    public SQClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public AuthenticationCalls newAuthenticationCalls() {
        return new AuthenticationCalls(httpClient, baseUrl);
    }

    public HotspotsCalls newHotspotsCalls() {
        return new HotspotsCalls(httpClient, baseUrl);
    }

    public IssuesCalls newIssuesCalls() {
        return new IssuesCalls(httpClient, baseUrl);
    }

    public ProjectsCalls newProjectsCalls() {
        return new ProjectsCalls(httpClient, baseUrl);
    }
}
