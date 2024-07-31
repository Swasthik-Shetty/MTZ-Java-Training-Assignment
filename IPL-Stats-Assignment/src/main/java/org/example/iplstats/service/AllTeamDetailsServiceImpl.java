package org.example.iplstats.service;

import org.example.iplstats.domain.TeamDetails;
import org.example.iplstats.dto.TeamDTO;

import java.util.ArrayList;
import java.util.List;

public class AllTeamDetailsServiceImpl implements AllTeamDetailsService {

    @Override
    public List<TeamDTO> getAllTeamDetails() {
        List<TeamDetails> teamDetailsList = JsonReaderUtil.loadJsonTeamData();
         List<TeamDTO> teamDTOList = new ArrayList<>();

        for(TeamDetails teamDetails : teamDetailsList)
        { TeamDTO teamDTO = new TeamDTO();
            teamDTO.setCity(teamDetails.getCity());
            teamDTO.setName(teamDetails.getName());
            teamDTO.setHome(teamDetails.getHome());
            teamDTO.setLabel(teamDetails.getLabel());
            teamDTO.setCoach(teamDetails.getCoach());
            teamDTOList.add(teamDTO);
        }
        return teamDTOList;
    }
}
