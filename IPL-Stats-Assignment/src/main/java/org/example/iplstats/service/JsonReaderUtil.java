package org.example.iplstats.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.iplstats.domain.PlayerDetails;
import org.example.iplstats.domain.TeamDetails;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public final class JsonReaderUtil {
    private JsonReaderUtil() {

    }
    public static List<TeamDetails> loadJsonTeamData() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<TeamDetails> teamDetailsList = new ArrayList<>();


        try {

            InputStream inputStream = JsonReaderUtil.class.getResourceAsStream("/ipl2020.json");
            assert inputStream != null;
            JsonNode node = objectMapper.readTree(inputStream);

            for(int i = 0 ; i< node.size();i++)
            {  TeamDetails teamDetails = new TeamDetails();
                teamDetails.setCity(node.get(i).get("city").asText());
                teamDetails.setCoach(node.get(i).get("coach").asText());
                teamDetails.setHome(node.get(i).get("home").asText());
                teamDetails.setName(node.get(i).get("name").asText());
                teamDetails.setLabel(node.get(i).get("label").asText());


                List<PlayerDetails> playerDetailsList = new ArrayList<>();
                for(int j=0;j<node.get(i).get("players").size();j++) {
                    PlayerDetails playerDetails = new PlayerDetails();
                    playerDetails.setName(node.get(i).get("players").get(j).get("name").asText());
                    playerDetails.setRole(node.get(i).get("players").get(j).get("role").asText());
                    playerDetails.setPrize(node.get(i).get("players").get(j).get("price").asLong());
                    playerDetailsList.add(playerDetails);
                }


                teamDetails.setPlayers(playerDetailsList);
                teamDetailsList.add(teamDetails);

            }





        } catch (IOException e) {
            System.out.println("IOException occurred");
        }

        return teamDetailsList;

    }

    public static List<PlayerDetails> loadJsonPlayerData() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<PlayerDetails> playerDetailsList = new ArrayList<>();


        try {

            InputStream inputStream = JsonReaderUtil.class.getResourceAsStream("/ipl2020.json");
            assert inputStream != null;
            JsonNode node = objectMapper.readTree(inputStream);

            for(int i = 0 ; i< node.size();i++)
            {  TeamDetails teamDetails = new TeamDetails();

                for(int j=0;j<node.get(i).get("players").size();j++) {
                    PlayerDetails playerDetails = new PlayerDetails();
                    playerDetails.setName(node.get(i).get("players").get(j).get("name").asText());
                    playerDetails.setRole(node.get(i).get("players").get(j).get("role").asText());
                    playerDetails.setPrize(node.get(i).get("players").get(j).get("price").asLong());
                    playerDetailsList.add(playerDetails);
                }

            }





        } catch (IOException e) {
            System.out.println("IOException occurred");
        }

        return playerDetailsList;

    }

}
