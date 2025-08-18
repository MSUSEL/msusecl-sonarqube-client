package api.issues.responseObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * This class represents the top-level JSON response from the SonarQube API.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Search {
    private Paging paging;
    private List<Issue> issues;
    private List<Component> components;
    private List<Rule> rules;
    private List<User> users;
    private List<Facet> facets;

    /**
     * Represents the "paging" object in the JSON response.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Paging {
        private int pageIndex;
        private int pageSize;
        private int total;
    }

    /**
     * Represents a single "issue" object.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Issue {
        private String key;
        private String component;
        private String project;
        private String rule;
        private String cleanCodeAttribute;
        private String cleanCodeAttributeCategory;
        private String issueStatus;
        private boolean prioritizedRule;
        private List<Impact> impacts;
        private String message;
        private List<MessageFormatting> messageFormattings;
        private int line;
        private String hash;
        private String author;
        private String effort;
        private String creationDate;
        private String updateDate;
        private List<String> tags;
        private List<Comment> comments;
        private List<String> transitions;
        private List<String> actions;
        private TextRange textRange;
        private List<Flow> flows;
        private boolean quickFixAvailable;
        private String ruleDescriptionContextKey;
        private List<String> codeVariants;
    }

    /**
     * Represents the "impact" object within an issue.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Impact {
        private String softwareQuality;
        private String severity;
    }

    /**
     * Represents a "messageFormattings" object within an issue.
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
     * Represents a "comment" object within an issue.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Comment {
        private String key;
        private String login;
        private String htmlText;
        private String markdown;
        private boolean updatable;
        private String createdAt;
    }

    /**
     * Represents the "textRange" object within an issue or a flow location.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TextRange {
        private int startLine;
        private int endLine;
        private int startOffset;
        private int endOffset;
    }

    /**
     * Represents a "flow" object within an issue.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Flow {
        private List<Location> locations;
    }

    /**
     * Represents a "location" object within a flow.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Location {
        private TextRange textRange;
        private String msg;
        private List<MessageFormatting> msgFormattings;
    }

    /**
     * Represents a single "component" object from the top-level "components" array.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Component {
        private String key;
        private boolean enabled;
        private String qualifier;
        private String name;
        private String longName;
        private String path;
    }

    /**
     * Represents a single "rule" object from the top-level "rules" array.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Rule {
        private String key;
        private String name;
        private String status;
        private String lang;
        private String langName;
    }

    /**
     * Represents a single "user" object from the top-level "users" array.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class User {
        private String login;
        private String name;
        private boolean active;
        private String avatar;
    }

    /**
     * Represents a single "facet" object from the top-level "facets" array.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Facet {
        private String property;
        private List<FacetValue> values;
    }

    /**
     * Represents a "value" object within a facet.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FacetValue {
        private String val;
        private int count;
    }
}
