package api.rules.responseObjects;

import lombok.Data;

import java.util.List;

@Data
public class Active {
    private String qProfile;
    private String inherit;
    private String severity;
    private boolean prioritizedRule;
    private List<ActiveParam> params;
}
