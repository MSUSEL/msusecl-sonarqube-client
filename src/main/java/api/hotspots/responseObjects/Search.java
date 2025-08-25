package api.hotspots.responseObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Search {

    private Paging paging;
    private List<Hotspot> hotspots;
    private List<Component> components;

    /**
     * Represents the paging information from the JSON.
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
     * Represents a single hotspot found in the analysis.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Hotspot {

        private String key;
        private String component;
        private String project;
        private String securityCategory;
        private String vulnerabilityProbability;
        private String status;
        private int line;
        private String message;
        private List<Object> messageFormattings;
        private String assignee;
        private String author;
        // Using ZonedDateTime to properly handle the date-time string with offset
        private ZonedDateTime creationDate;
        private ZonedDateTime updateDate;
        private List<Object> flows;
        private String ruleKey;
    }

    /**
     * Represents a single component from the analysis, such as a file or project.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Component {

        private String key;
        private String qualifier;
        private String name;
        private String longName;
        // The 'path' field is optional in the JSON, so it can be null.
        private String path;
    }
}
