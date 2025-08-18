package api.projects.responseObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Search {
    private Paging paging;
    private List<Component> components;

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
     * Represents a single component object from the "components" array.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Component {
        private String key;
        private String name;
        private String qualifier;
        private String visibility;
        private String lastAnalysisDate;
        private String revision;
        private boolean managed;
    }
}

