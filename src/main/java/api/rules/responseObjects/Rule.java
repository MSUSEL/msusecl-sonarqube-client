package api.rules.responseObjects;

import lombok.Data;
import java.util.List;

@Data
public class Rule {
    private String key;
    private String repo;
    private String name;
    private String htmlDesc;
    private String severity;
    private String status;
    private String internalKey;
    private boolean template;
    private List<String> tags;
    private List<String> sysTags;
    private String remFnType;
    private String remFnGapMultiplier;
    private String remFnBaseEffort;
    private String defaultRemFnType;
    private String defaultRemFnGapMultiplier;
    private String defaultRemFnBaseEffort;
    private boolean remFnOverloaded;
    private String gapDescription;
    private String lang;
    private String langName;
    private String scope;
    private boolean isExternal;
    private String type;
    private String cleanCodeAttributeCategory;
    private String cleanCodeAttribute;
    private List<Impact> impacts;
    private List<DescriptionSection> descriptionSections;
    private List<RuleParam> params;
}