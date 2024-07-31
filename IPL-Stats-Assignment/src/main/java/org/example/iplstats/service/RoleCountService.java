package org.example.iplstats.service;

import org.example.iplstats.dto.RoleCountDTO;

import java.util.List;

public interface RoleCountService {
    public List<RoleCountDTO> getCountByRole(String label);
}
