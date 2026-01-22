package api.rules.responseObjects;

import lombok.Data;

@Data
public class DescriptionSection {
    private String key;
    private String content;
    private Context context;
}
