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

import api.AdvancedBuilderFacade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.Map;
import api.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdvancedBuilderFacadeTests {

    public static final Logger LOGGER = LoggerFactory.getLogger(AdvancedBuilderFacadeTests.class);
    String serverURL = System.getenv("SONAR_QUBE_SERVER");
    String apiKey = System.getenv("SONAR_QUBE_KEY");
    Map<String,String> testParams = Map.of("p", "1", "ps", "500");

    @Test
    public void testExecuteCallBuildURLCorrectly() {
        try{
            AdvancedBuilderFacade advancedBuilderFacade = new AdvancedBuilderFacade(HttpClient.newHttpClient(), serverURL, apiKey);
            HttpResponse<String> httpResp =  advancedBuilderFacade.executeCall(testParams, Constants.API_ISSUES_SEARCH);
            Assertions.assertNotNull(httpResp);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            Assertions.fail();
        }
    }

}
