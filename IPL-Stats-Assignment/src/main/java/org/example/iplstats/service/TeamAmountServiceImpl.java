package org.example.iplstats.service;

import org.example.iplstats.domain.PlayerDetails;
import org.example.iplstats.domain.TeamDetails;
import org.example.iplstats.dto.TeamAmountDTO;

import java.util.ArrayList;
import java.util.List;

public class TeamAmountServiceImpl implements TeamAmountService{

    @Override
    public List<TeamAmountDTO> getTotalAmountSpentByTeam() {

        List<TeamAmountDTO> teamAmountDTOList = new ArrayList<>();
        List<TeamDetails> teamDetailsList = JsonReaderUtil.loadJsonTeamData();

        for(TeamDetails team : teamDetailsList)
        {
            TeamAmountDTO teamAmountDTO = new TeamAmountDTO();
            teamAmountDTO.setLabel(team.getLabel());
            long amount = 0;

            List<PlayerDetails> playerDetails= team.getPlayers();

                for(PlayerDetails player : playerDetails) {
                   amount+= player.getPrize();
                }
                teamAmountDTO.setAmount(amount);
                teamAmountDTOList.add(teamAmountDTO);
        }
        return teamAmountDTOList;
    }
}
