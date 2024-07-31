package org.example.iplstats.service;

import org.example.iplstats.domain.PlayerDetails;
import org.example.iplstats.dto.PlayerDTO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortPlayerDetailsserviceImpl implements SortPlayerDetailsservice{
    @Override
    public List<PlayerDTO> getPlayersBySort(String fieldName) {
        List<PlayerDetails> playerDetailsList = JsonReaderUtil.loadJsonPlayerData();
        List<PlayerDTO> playerDTOList = new ArrayList<>();

            for(PlayerDetails player : playerDetailsList) {
                PlayerDTO playerDTO = new PlayerDTO();
                playerDTO.setName(player.getName());
                playerDTO.setRole(player.getRole());
                playerDTO.setPrize(player.getPrize());
                playerDTOList.add(playerDTO);
            }

        switch(fieldName)
        {   case "role":
            playerDTOList.sort(Comparator.comparing(PlayerDTO::getRole));
            break;
            case "price":
                playerDTOList.sort(Comparator.comparingLong(PlayerDTO::getPrize));
                break;
            case "name":
                playerDTOList.sort(Comparator.comparing(PlayerDTO::getName));
                break;
            default:
                break;

        }
        return playerDTOList;
    }
}
