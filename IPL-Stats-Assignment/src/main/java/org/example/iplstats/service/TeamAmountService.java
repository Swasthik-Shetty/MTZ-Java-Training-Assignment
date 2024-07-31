package org.example.iplstats.service;

import org.example.iplstats.dto.TeamAmountDTO;

import java.util.List;

public interface TeamAmountService {
    public List<TeamAmountDTO> getTotalAmountSpentByTeam();
}
