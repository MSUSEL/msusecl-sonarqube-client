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
    public static String API_PROJECTS_CREATE = "/api/projects/Create";
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

    // logging constants
    public static String REQUEST_FAILED = "The HTTP Request failed with the following error: ";

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
}
