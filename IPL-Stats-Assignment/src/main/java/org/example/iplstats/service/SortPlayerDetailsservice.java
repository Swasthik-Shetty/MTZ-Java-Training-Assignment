package org.example.iplstats.service;

import org.example.iplstats.dto.PlayerDTO;

import java.util.List;

public interface SortPlayerDetailsservice {
    public List<PlayerDTO> getPlayersBySort(String fieldName);
}
