package org.example.iplstats.service;

import org.example.iplstats.dto.PlayerDTO;
import org.example.iplstats.dto.RoleCountDTO;
import org.example.iplstats.dto.TeamAmountDTO;
import org.example.iplstats.dto.TeamDTO;

import java.util.List;
import java.util.Map;

public interface AllTeamDetailsService {
    public List<TeamDTO> getAllTeamDetails();
    public Map<String, List<PlayerDTO>> getMaxPaidPlayersByRole();
    public List<PlayerDTO> getPlayersByTeam(String role, String label);
    public List<RoleCountDTO> getCountByRole(String label);
    public List<PlayerDTO> getPlayersBySort(String fieldName);
    public void getTeamAmountByRole(String label, String role);
    public List<TeamAmountDTO> getTotalAmountSpentByTeam();
    public List<String> getTeamLabels();
    public List<PlayerDTO> getPlayersByTeam(String label);

}
