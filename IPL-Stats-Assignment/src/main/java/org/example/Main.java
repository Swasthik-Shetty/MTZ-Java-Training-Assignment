package org.example;


import org.example.iplstats.dto.PlayerDTO;
import org.example.iplstats.dto.RoleCountDTO;
import org.example.iplstats.dto.TeamAmountDTO;
import org.example.iplstats.dto.TeamDTO;
import org.example.iplstats.service.*;


import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

//        Question 1
        TeamLabelsServiceImpl teamLabelsService =  new TeamLabelsServiceImpl();
        List<String> labels = teamLabelsService.getTeamLabels();
        System.out.println("The team labels are:");
        for(String label : labels) {
            System.out.print(label + " ");
        }

////        //Question 2
        TeamPlayersServiceImpl teamPlayersService = new TeamPlayersServiceImpl();
        List<PlayerDTO> playerDTOList = teamPlayersService.getPlayersByTeam("MI");
        System.out.println("The team players are:");
        for(PlayerDTO player : playerDTOList) {
            System.out.println("Name : " + player.getName() + " Role: " + player.getRole());
        }
//
//        Question 3

        RoleCountServiceImpl roleCountService = new RoleCountServiceImpl();
        List<RoleCountDTO> roleCountDTOS = roleCountService.getCountByRole("MI");
        for(RoleCountDTO roleCountDTO : roleCountDTOS)
        {
            System.out.println(roleCountDTO.getRoleName()+":"+roleCountDTO.getCount());
        }
//       // Question 4
            PlayerDetailsByRoleAndTeamServiceImpl playerDetailsByRoleAndTeam = new PlayerDetailsByRoleAndTeamServiceImpl();
            List<PlayerDTO> playerDTOS = playerDetailsByRoleAndTeam.getPlayersByTeam("Batsman","MI");
            for(PlayerDTO player : playerDTOS) {
             System.out.println("Name : " + player.getName() + " Prize: " + player.getPrize());
            }

            //Question 5
            AllTeamDetailsServiceImpl allTeamDetailsService = new AllTeamDetailsServiceImpl();
            List<TeamDTO> teamDTOS = allTeamDetailsService.getAllTeamDetails();
            for(TeamDTO team : teamDTOS)
            {
                System.out.println("Name : " + team.getName() + " Label: " + team.getLabel() + " Coach: " + team.getCoach() + "City : " + team.getCity() + "Home:"+team.getHome());
            }

            //Question 6
            TeamAmountServiceImpl teamAmountService = new TeamAmountServiceImpl();
            List<TeamAmountDTO> teamAmountDTOS = teamAmountService.getTotalAmountSpentByTeam();
            for(TeamAmountDTO team : teamAmountDTOS) {
                System.out.println("Team Label : "+team.getLabel() + " Total amount spent ; " + team.getAmount());
            }

            //Question 7
            TeamAmountByRoleServiceImpl teamAmountByRoleService = new TeamAmountByRoleServiceImpl();
            teamAmountByRoleService.getTeamAmountByRole("MI","Batsman");

            //Question 8
            SortPlayerDetailsserviceImpl sortPlayerDetailsservice = new SortPlayerDetailsserviceImpl();
            List<PlayerDTO> playerDTOS1 = sortPlayerDetailsservice.getPlayersBySort("name");
            for(PlayerDTO player : playerDTOS1)
            {
                System.out.println("Name : " + player.getName() +" Role :" + player.getRole() + " Price: " + player.getPrize());
            }

        //Question 9
          MaxPaidPlayerByRoleServiceImpl maxPaidPlayerByRoleService = new MaxPaidPlayerByRoleServiceImpl();
          Map<String,List<PlayerDTO>> maxPaidPlayerByRole = maxPaidPlayerByRoleService.getMaxPaidPlayersByRole();

        for (Map.Entry<String, List<PlayerDTO>> entry : maxPaidPlayerByRole.entrySet()) {
            String role = entry.getKey();
            List<PlayerDTO> players = entry.getValue();
            System.out.println("Role: " + role);
            for (PlayerDTO player : players) {
                System.out.println("  Name: " + player.getName() + ", Price: " + player.getPrize());
            }
        }

    }
}
