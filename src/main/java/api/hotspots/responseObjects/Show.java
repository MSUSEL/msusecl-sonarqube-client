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
package api.hotspots.responseObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Top-level class representing the full JSON response for a single issue.
 * It contains details about the issue, its associated components, and change history.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Show {
    private String key;
    private Component component;
    private Project project;
    private Rule rule;
    private String status;
    private int line;
    private String hash;
    private String message;
    private List<MessageFormatting> messageFormattings;
    private String assignee;
    private String author;
    private ZonedDateTime creationDate;
    private ZonedDateTime updateDate;
    private List<ChangelogEntry> changelog;
    private List<Comment> comment;
    private List<User> users;
    private boolean canChangeStatus;
    private List<String> codeVariants;

    /**
     * Represents the component of the issue (e.g., a file).
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Component {
        private String key;
        private String qualifier;
        private String name;
        private String longName;
        private String path;
    }

    /**
     * Represents the project the issue belongs to.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Project {
        private String key;
        private String qualifier;
        private String name;
        private String longName;
    }

    /**
     * Represents the rule that triggered the issue.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Rule {
        private String key;
        private String name;
        private String securityCategory;
        private String vulnerabilityProbability;
    }

    /**
     * Represents a formatting instruction within a message.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MessageFormatting {
        private int start;
        private int end;
        private String type;
    }

    /**
     * Represents a single entry in the issue's changelog.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChangelogEntry {
        private String user;
        private String userName;
        private ZonedDateTime creationDate;
        private List<Diff> diffs;
        private String avatar;
        private boolean isUserActive;
    }

    /**
     * Represents a single change or diff within a changelog entry.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Diff {
        private String key;
        private String newValue;
        private String oldValue;
    }

    /**
     * Represents a comment on the issue.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Comment {
        private String key;
        private String login;
        private String htmlText;
        private String markdown;
        private ZonedDateTime createdAt;
    }

    /**
     * Represents a user associated with the issue.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class User {
        private String login;
        private String name;
        private boolean active;
    }
}
