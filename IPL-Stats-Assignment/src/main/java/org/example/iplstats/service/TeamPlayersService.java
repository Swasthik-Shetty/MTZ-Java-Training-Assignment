package org.example.iplstats.service;

import org.example.iplstats.dto.PlayerDTO;

import java.util.List;

public interface TeamPlayersService {
    public List<PlayerDTO> getPlayersByTeam(String label);
}
