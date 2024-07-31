package org.example.iplstats.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class TeamAmountByRoleDTO {
    private String label;
    private String role;
    private long amount;
}
