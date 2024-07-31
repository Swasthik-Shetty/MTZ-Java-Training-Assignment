package org.example.iplstats.service;

import org.example.iplstats.domain.PlayerDetails;
import org.example.iplstats.domain.TeamDetails;

import java.util.List;

public class TeamAmountByRoleServiceImpl implements TeamAmountByRoleService{
    @Override
    public void getTeamAmountByRole(String label, String role) {

        List<TeamDetails> teamDetailsList = JsonReaderUtil.loadJsonTeamData();

        for(TeamDetails team : teamDetailsList)
        {
            if(team.getLabel().equals(label))
            {  List<PlayerDetails> playerDetails = team.getPlayers();
                long amount = 0;
                for(PlayerDetails player : playerDetails)
                {
                    if(player.getRole().equals(role)) {
                        amount+=player.getPrize();
                    }
                }
                System.out.println("Amount spent on "+role+" role by team "+ label +" is : "+amount);
            }
        }
    }
}
