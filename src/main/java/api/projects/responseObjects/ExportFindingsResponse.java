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
