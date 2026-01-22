package api.rules.responseObjects;

import lombok.Data;

@Data
public class RuleParam {
    private String key;
    private String desc;
    private String defaultValue;
}
