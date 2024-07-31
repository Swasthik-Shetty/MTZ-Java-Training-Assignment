package org.example.iplstats.service;

import org.example.iplstats.dto.PlayerDTO;

import java.util.List;
import java.util.Map;

public interface MaxPaidPlayerByRoleService {
    public Map<String, List<PlayerDTO>> getMaxPaidPlayersByRole();
}
