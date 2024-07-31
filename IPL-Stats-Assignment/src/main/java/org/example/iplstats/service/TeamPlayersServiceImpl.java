package org.example.iplstats.service;

import org.example.iplstats.domain.PlayerDetails;
import org.example.iplstats.domain.TeamDetails;
import org.example.iplstats.dto.PlayerDTO;

import java.util.ArrayList;
import java.util.List;

public class TeamPlayersServiceImpl implements TeamPlayersService{
    @Override
    public List<PlayerDTO> getPlayersByTeam(String label) {

        List<PlayerDTO> playerDTOList= new ArrayList<>();
        List<TeamDetails> teamDetailsList = JsonReaderUtil.loadJsonTeamData();
        for(TeamDetails team : teamDetailsList)
        {
            if(team.getLabel().equals(label))
            {
                List<PlayerDetails> playerDetails= team.getPlayers();
                for(PlayerDetails player : playerDetails) {
                    PlayerDTO playerDTO = new PlayerDTO();
                    playerDTO.setName(player.getName());
                    playerDTO.setRole(player.getRole());
                    playerDTO.setPrize(player.getPrize());
                    playerDTOList.add(playerDTO);
                }
            }
        }

        return playerDTOList;
    }
}
