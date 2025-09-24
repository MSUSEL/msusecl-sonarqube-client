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
package api.authentication;

import api.BaseCalls;
import api.SimpleRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import static api.Constants.*;

/**
 * Hit SonarQube's api/authentication/* endpoints
 */
public class AuthenticationCalls extends BaseCalls {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationCalls.class);
    private final SimpleRequest simpleRequest;

    public AuthenticationCalls(HttpClient httpClient, String baseUrl) {
        super(httpClient, baseUrl);
        this.simpleRequest = new SimpleRequest(httpClient, baseUrl, timeout);
    }

    /**
     * Authenticate a user
     * @param username SonarQube "login" parameter
     * @param password SonarQube password
     * @return API Response
     */
    public HttpResponse<String> login(String username, String password) {
        String formData = LOGIN_PARAM + username + "&" + PASSWORD_PARAM + password;
        return simpleRequest.sendPostRequest(formData, API_AUTHENTICATION_LOGIN);
    }

    /**
     * Logout a user
     * @return API Response
     */
    public HttpResponse<String> logout() {
        return simpleRequest.sendPostRequest(API_AUTHENTICATION_LOGOUT);
    }

    /**
     * Check credentials
     * @return API Response
     */
    public HttpResponse<String> validate() {
        URI uri = URI.create(baseUrl + API_AUTHENTICATION_VALIDATE);
        return simpleRequest.sendGetRequest(uri);
    }
}
