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
