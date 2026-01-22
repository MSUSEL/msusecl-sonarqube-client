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
import java.time.Duration;

import static api.Constants.BAD_STATUS_MESSAGE;
import static api.Constants.GOOD_STATUS_MESSAGE;

public class BaseCalls {
    protected final HttpClient httpClient;
    protected final String baseUrl;
    protected String token = "";
    protected final Duration timeout = Duration.ofSeconds(30);
    protected final IResponseHandler<String> responseHandler = new ResponseHandler();
    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseCalls.class);

    public BaseCalls(HttpClient httpClient, String baseUrl, String token) {
        this.httpClient = httpClient;
        this.baseUrl = baseUrl;
        this.token = token;
    }

    public BaseCalls(HttpClient httpClient, String baseUrl) {
        this.httpClient = httpClient;
        this.baseUrl = baseUrl;
    }

    public void logResponseStatusCode(HttpResponse<String> response) {
        if (!responseHandler.checkSuccess(response)) {
            LOGGER.error(BAD_STATUS_MESSAGE, response.statusCode());
        } else {
            LOGGER.info(GOOD_STATUS_MESSAGE, response.statusCode());
        }
    }
}
