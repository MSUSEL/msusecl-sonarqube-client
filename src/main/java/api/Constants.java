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

public class Constants {
    public static String AUTH = "Authorization";
    public static String BEARER = "Bearer";
    public static String CONT_TYPE = "Content-Type";
    public static String APP_FORM = "application/x-www-form-urlencoded";

    // api/authentication endpoints
    public static String API_AUTHENTICATION_LOGIN = "/api/authentication/login";
    public static String API_AUTHENTICATION_LOGOUT = "/api/authentication/logout";
    public static String API_AUTHENTICATION_VALIDATE = "/api/authentication/validate";

    // api/projects endpoints
    public static String API_PROJECTS_BULK_DELETE = "/api/projects/bulk_delete";
    public static String API_PROJECTS_CREATE = "/api/projects/create";
    public static String API_PROJECTS_DELETE = "/api/projects/delete";
    public static String API_PROJECTS_EXPORT_FINDINGS = "/api/projects/export_findings";
    public static String API_PROJECTS_GET_CONTAINS_AI_CODE = "/api/projects/get_contains_ai_code";
    public static String API_PROJECTS_LICENSE_USAGE = "/api/projects/license_usage";
    public static String API_PROJECTS_SEARCH = "/api/projects/search";
    public static String API_PROJECTS_SET_CONTAINS_AI_CODE = "/api/projects/set_contains_ai_code";
    public static String API_PROJECTS_UPDATE_KEY = "/api/projects/update_key";
    public static String API_PROJECTS_UPDATE_VISIBILITY = "/api/projects/update_visibility";

    // api/issues endpoints
    public static String API_ISSUES_SEARCH = "/api/issues/search";

    // api/hotspots endpoints
    public static String API_HOTSPOTS_CHANGE_STATUS = "/api/hotspots/change_status";
    public static String API_HOTSPOTS_SEARCH = "/api/hotspots/search";
    public static String API_HOTSPOTS_SHOW = "/api/hotspots/show";

    // api/rules endpoints
    public static String API_RULES_SHOW = "/api/rules/show";

    // logging constants
    public static String REQUEST_FAILED = "The HTTP Request failed with the following error: ";
    public static String BAD_STATUS_MESSAGE = "Request failed with status code: {}";
    public static String GOOD_STATUS_MESSAGE = "Request succeeded with status code: {}";

    // request parameters
    public static String PROJECT_PARAM = "project=";
    public static String PROJECTS_PARAM = "projects=";
    public static String NAME_PARAM = "name=";
    public static String CONTAINS_AI_CODE_PARAM = "contains_ai_code=";
    public static String FROM_PARAM = "from=";
    public static String TO_PARAM = "to=";
    public static String VISIBILITY_PARAM = "visibility=";
    public static String LOGIN_PARAM = "login=";
    public static String PASSWORD_PARAM = "password=";
    public static String STATUS_PARAM = "status=";
    public static String HOTSPOT_PARAM = "hotspot=";
    public static String RULES_PARAM = "key=";
}
