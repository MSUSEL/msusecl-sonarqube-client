package api.projects.responseObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Top-level JSON response, which contains
 * a list of projects.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LicenseUsage {
    private List<Project> projects;

    /**
     * Single project object from the "projects" array.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Project {
        private String projectKey;
        private String projectName;
        private String branch;
        private String pullRequest; // Optional in the JSON.
        private int linesOfCode;
        private double licenseUsagePercentage;
    }
}
