package org.example.iplstats.service;

import org.example.iplstats.domain.TeamDetails;

import java.util.ArrayList;
import java.util.List;

public class TeamLabelsServiceImpl implements TeamLabelsService {

    @Override
    public List<String> getTeamLabels() {
        List<TeamDetails> teamDetailsList = JsonReaderUtil.loadJsonTeamData();
        List<String> labels = new ArrayList<>();
        for(TeamDetails team : teamDetailsList) {
            labels.add(team.getLabel());

        }
        return labels;
    }
}
