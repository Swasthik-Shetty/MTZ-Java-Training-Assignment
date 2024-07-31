package org.example.iplstats.service;

import org.example.iplstats.dto.PlayerDTO;

import java.util.List;

public interface PlayerDetailsByRoleAndTeamService {
    public List<PlayerDTO> getPlayersByTeam(String role, String label);
}
