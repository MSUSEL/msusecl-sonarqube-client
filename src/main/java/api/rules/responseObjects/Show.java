package api.rules.responseObjects;

import lombok.Data;

import java.util.List;

@Data
public class Show {
    private Rule rule;
    List<Active> active;
}
