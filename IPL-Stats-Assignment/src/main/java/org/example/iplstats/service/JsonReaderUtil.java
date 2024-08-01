package org.example.iplstats.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.iplstats.domain.PlayerDetails;
import org.example.iplstats.domain.TeamDetails;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

            teamDetailsList = StreamSupport.stream(node.spliterator(), false)
                                .map(teamNode -> {
                                    TeamDetails teamDetails = new TeamDetails();
                                    teamDetails.setCoach(teamNode.get("coach").asText());
                                    teamDetails.setCity(teamNode.get("city").asText());
                                    teamDetails.setLabel(teamNode.get("label").asText());
                                    teamDetails.setName(teamNode.get("name").asText());
                                    teamDetails.setHome(teamNode.get("home").asText());

                                    List<PlayerDetails> playerDetails = StreamSupport.stream(teamNode.get("players").spliterator(), false)
                                            .map(player -> {
                                                PlayerDetails playerDetail = new PlayerDetails();
                                                playerDetail.setRole(player.get("role").asText());
                                                playerDetail.setName(player.get("name").asText());
                                                playerDetail.setPrize(player.get("price").asLong());
                                                return playerDetail;
                                            })
                                            .collect(Collectors.toList());

                                    teamDetails.setPlayers(playerDetails);
                                    return teamDetails;
                                })
                                .collect(Collectors.toList());


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

            for (int i = 0; i < node.size(); i++) {

                for (int j = 0; j < node.get(i).get("players").size(); j++) {
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
