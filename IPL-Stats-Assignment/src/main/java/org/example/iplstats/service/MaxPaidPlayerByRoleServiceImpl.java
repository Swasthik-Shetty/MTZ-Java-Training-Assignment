package org.example.iplstats.service;

import org.example.iplstats.domain.PlayerDetails;
import org.example.iplstats.domain.TeamDetails;
import org.example.iplstats.dto.PlayerDTO;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaxPaidPlayerByRoleServiceImpl implements MaxPaidPlayerByRoleService{
    private final ModelMapper modelMapper = new ModelMapper();
    @Override
    public Map<String, List<PlayerDTO>> getMaxPaidPlayersByRole() {

            List<PlayerDetails> playerDetails = JsonReaderUtil.loadJsonPlayerData();
            Map<String, List<PlayerDTO>> maxPaidPlayersByRole = new HashMap<>();

            playerDetails.forEach(player -> {
                        PlayerDTO playerDTO = modelMapper.map(player, PlayerDTO.class);
                        maxPaidPlayersByRole.merge(player.getRole(), new ArrayList<>(List.of(playerDTO)), (existing, newList) -> {
                            if (player.getPrize() > existing.get(0).getPrize()) {
                                return newList;
                            } else if (player.getPrize() == existing.get(0).getPrize()) {
                                existing.add(playerDTO);
                            }
                            return existing;
                        });
                    });
            return maxPaidPlayersByRole;
        }

}
