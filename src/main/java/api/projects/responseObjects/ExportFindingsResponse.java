package api.projects.responseObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents the top-level JSON response, containing a list of findings.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExportFindingsResponse {
    private List<Finding> export_findings;

    /**
     * Represents a single finding within the "export_findings" array.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Finding {
        private String key;
        private String projectKey;
        private String branch;
        private String path;
        private String lineNumber;
        private String message;
        private String issueStatus;
        private String author;
        private String assignee;
        private String createdAt;
        private String updatedAt;
        private String ruleReference;
        private List<Comment> comments;
        private String effort;
        private String tags;
        private String cleanCodeAttribute;
        private String cleanCodeAttributeCategory;
        private List<Impact> impacts;
        private String type;
        private String status;
        private String resolution;
        private String securityCategory;
        private String vulnerabilityProbability;
    }

/**
 * Represents a comment associated with a finding.
 */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Comment {
        private String key;
        private String message;
        private String author;
        private String createdAt;
        private String updatedAt;
    }

    /**
     * Represents the impact details of a finding.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Impact {
        private String softwareQuality;
        private String severity;
    }
}
