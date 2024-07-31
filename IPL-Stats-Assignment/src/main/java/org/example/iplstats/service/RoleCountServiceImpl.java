package org.example.iplstats.service;

import org.example.iplstats.domain.PlayerDetails;
import org.example.iplstats.domain.TeamDetails;
import org.example.iplstats.dto.RoleCountDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleCountServiceImpl implements RoleCountService {

    @Override
    public List<RoleCountDTO>  getCountByRole(String label) {

        List<RoleCountDTO> roleCountDTOList = new ArrayList<>();

        List<TeamDetails> teamDetailsList =JsonReaderUtil.loadJsonTeamData();



        for (TeamDetails teamDetail : teamDetailsList) {

            if ( teamDetail.getLabel().equals(label)) {

                List<PlayerDetails> playerDetails=teamDetail.getPlayers();
                HashMap<String, Integer> roleCountMap = new HashMap<>();

                for (PlayerDetails playerDetail : playerDetails) {
                    String role = playerDetail.getRole();
                    roleCountMap.put(role, roleCountMap.getOrDefault(role, 0) + 1);
                }

                for (Map.Entry<String, Integer> entry : roleCountMap.entrySet()) {
                    RoleCountDTO dto = new RoleCountDTO();
                    dto.setRoleName(entry.getKey());
                    dto.setCount(entry.getValue());
                    roleCountDTOList.add(dto);
                }
            }
        }





        return roleCountDTOList;


    }
}
