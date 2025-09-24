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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.Map;

public class AdvancedBuilderFacade extends BaseCalls {
    private static final Logger logger = LoggerFactory.getLogger(AdvancedBuilderFacade.class);
    private String token = "";
    private final String baseUrl;
    private final HttpClient httpClient;
    private final AdvancedRequestBuilder builder;

    public AdvancedBuilderFacade(HttpClient httpClient, String baseUrl, String token) {
        super(httpClient, baseUrl, token);

        this.token = token;
        this.baseUrl = baseUrl;
        this.httpClient = httpClient;
        this.builder = new AdvancedRequestBuilder(httpClient, baseUrl, token);
    }

    public AdvancedBuilderFacade(HttpClient httpClient, String baseUrl) {
        super(httpClient, baseUrl);

        this.baseUrl = baseUrl;
        this.httpClient = httpClient;
        this.builder = new AdvancedRequestBuilder(httpClient, baseUrl);
    }

    /**
     * Execute a custom-built call
     * @param params Map of key, value pairs corresponding to SonarQube endpoint parameters
     *      documentation here: <a href="https://next.sonarqube.com/sonarqube/web_api/api/issues">...</a>
     * @return API Response
     */
    public HttpResponse<String> executeCall(Map<String, String> params, String endpoint) {
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.param(entry.getKey(), entry.getValue());
        }

        try {
            return builder.endpoint(baseUrl + endpoint).execute();
        } catch (Exception e) {
            logger.error("API request failed to send", e);
            throw new RuntimeException(e);
        }

    }
}
