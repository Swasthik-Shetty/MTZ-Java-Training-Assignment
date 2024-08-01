package org.example.iplstats.service;

import org.example.iplstats.domain.PlayerDetails;
import org.example.iplstats.domain.TeamDetails;
import org.example.iplstats.dto.PlayerDTO;
import org.example.iplstats.dto.RoleCountDTO;
import org.example.iplstats.dto.TeamAmountDTO;
import org.example.iplstats.dto.TeamDTO;
import org.modelmapper.ModelMapper;

import java.util.*;
import java.util.stream.Collectors;

public class IPLStatsServiceImpl implements IPLStatsService {
    List<TeamDetails> teamDetailsList = JsonReaderUtil.loadJsonTeamData();
    List<PlayerDetails> playerDetails = JsonReaderUtil.loadJsonPlayerData();

    private final ModelMapper modelMapper = new ModelMapper();


    @Override
    public List<TeamDTO> getAllTeamDetails() {

        return teamDetailsList.stream()
                .map(teamDetails -> {
                    TeamDTO teamDTO = new TeamDTO();
                    teamDTO.setCity(teamDetails.getCity());
                    teamDTO.setName(teamDetails.getName());
                    teamDTO.setHome(teamDetails.getHome());
                    teamDTO.setLabel(teamDetails.getLabel());
                    teamDTO.setCoach(teamDetails.getCoach());
                    return teamDTO;
                })
                .collect(Collectors.toList());

    }


    @Override
    public Map<String, List<PlayerDTO>> getMaxPaidPlayersByRole() {

        Map<String, List<PlayerDTO>> maxPaidPlayersByRole = new HashMap<>();

        playerDetails.forEach(player -> {
            PlayerDTO playerDTO = modelMapper.map(player, PlayerDTO.class);
            maxPaidPlayersByRole.merge(player.getRole(), new ArrayList<>(List.of(playerDTO)), (existing, newList) -> {
                if (player.getPrize() > existing.get(0).getPrize()) {
                    return newList;
                } else if (player.getPrize() == existing.get(0).getPrize()) {
                    existing.add(playerDTO);
                }
                return existing;
            });
        });

        return maxPaidPlayersByRole;
    }

    @Override
    public List<PlayerDTO> getPlayersByTeam(String role, String label) {

        List<TeamDetails> teamDetailsList = JsonReaderUtil.loadJsonTeamData();
        return teamDetailsList.stream()
                .filter(team -> team.getLabel().equals(label))
                .flatMap(team -> team.getPlayers().stream())
                .filter(player -> player.getRole().equals(role))
                .map(player -> {
                    PlayerDTO playerDTO = new PlayerDTO();
                    playerDTO.setName(player.getName());
                    playerDTO.setRole(player.getRole());
                    playerDTO.setPrize(player.getPrize());
                    return playerDTO;
                })
                .collect(Collectors.toList());

    }

    @Override
    public List<RoleCountDTO> getCountByRole(String label) {


        return teamDetailsList.stream()
                .filter(teamDetail -> teamDetail.getLabel().equals(label))
                .flatMap(teamDetail -> teamDetail.getPlayers().stream())
                .collect(Collectors.groupingBy(PlayerDetails::getRole, Collectors.counting()))
                .entrySet()
                .stream()
                .map(entry -> {
                    RoleCountDTO dto = new RoleCountDTO();
                    dto.setRoleName(entry.getKey());
                    dto.setCount(entry.getValue().intValue());
                    return dto;
                })
                .collect(Collectors.toList());


    }

    @Override
    public List<PlayerDTO> getPlayersBySort(String fieldName) {
        List<PlayerDetails> playerDetailsList = JsonReaderUtil.loadJsonPlayerData();


        List<PlayerDTO> playerDTOList = playerDetailsList.stream()
                .map(player -> {
                    PlayerDTO playerDTO = new PlayerDTO();
                    playerDTO.setName(player.getName());
                    playerDTO.setRole(player.getRole());
                    playerDTO.setPrize(player.getPrize());
                    return playerDTO;
                })
                .collect(Collectors.toList());

        return switch (fieldName) {
            case "role" -> playerDTOList.stream().sorted(Comparator.comparing(PlayerDTO::getRole)).toList();
            case "price" -> playerDTOList.stream().sorted(Comparator.comparingLong(PlayerDTO::getPrize)).toList();
            case "name" -> playerDTOList.stream().sorted(Comparator.comparing(PlayerDTO::getName)).toList();
            default -> playerDTOList;
        };
    }

    @Override
    public void getTeamAmountByRole(String label, String role) {


        System.out.println("Amount spent on " + role + " role by team " + label + " is: " +
                teamDetailsList.stream()
                        .filter(team -> team.getLabel().equals(label))
                        .flatMap(team -> team.getPlayers().stream())
                        .filter(player -> player.getRole().equals(role))
                        .mapToLong(PlayerDetails::getPrize)
                        .sum());
    }

    @Override
    public List<TeamAmountDTO> getTotalAmountSpentByTeam() {

        return teamDetailsList.stream()
                .map(team -> {
                    TeamAmountDTO dto = new TeamAmountDTO();
                    dto.setLabel(team.getLabel());
                    dto.setAmount(team.getPlayers().stream().mapToLong(PlayerDetails::getPrize).sum());
                    return dto;
                })
                .collect(Collectors.toList());

    }

    @Override
    public List<String> getTeamLabels() {
        List<TeamDetails> teamDetailsList = JsonReaderUtil.loadJsonTeamData();
        return teamDetailsList.stream()
                .map(TeamDetails::getLabel)
                .collect(Collectors.toList());
    }

    @Override
    public List<PlayerDTO> getPlayersByTeam(String label) {


        List<TeamDetails> teamDetailsList = JsonReaderUtil.loadJsonTeamData();
        return teamDetailsList.stream()
                .filter(team -> team.getLabel().equals(label))
                .flatMap(team -> team.getPlayers().stream())
                .map(player -> {
                    PlayerDTO playerDTO = new PlayerDTO();
                    playerDTO.setName(player.getName());
                    playerDTO.setRole(player.getRole());
                    playerDTO.setPrize(player.getPrize());
                    return playerDTO;
                })
                .collect(Collectors.toList());


    }

}
