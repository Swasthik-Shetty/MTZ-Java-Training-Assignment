package org.example.iplstats.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.iplstats.domain.PlayerDetails;

import java.util.List;

public class RoleCountServiceImpl implements RoleCountService {

    @Override
    public void getCountByRole(String label) {
         List<PlayerDetails> playerDetailsList = JsonReaderUtil.loadJsonPlayerData();
         PlayerDetails playerDetails = new PlayerDetails();
    }
}
