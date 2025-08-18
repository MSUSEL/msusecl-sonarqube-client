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

    public AuthenticationCalls(HttpClient httpClient, String baseUrl, String token) {
        super(httpClient, baseUrl, token);
        this.simpleRequest = new SimpleRequest(httpClient, baseUrl, token, timeout);
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
