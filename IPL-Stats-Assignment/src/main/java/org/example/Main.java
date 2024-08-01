package org.example;


import org.example.iplstats.dto.PlayerDTO;
import org.example.iplstats.dto.RoleCountDTO;
import org.example.iplstats.dto.TeamAmountDTO;
import org.example.iplstats.dto.TeamDTO;
import org.example.iplstats.service.*;


import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        IPLStatsServiceImpl iplStatsService = new IPLStatsServiceImpl();

//        Question 1

        List<String> labels = iplStatsService.getTeamLabels();
        System.out.println("The team labels are:");
        labels.forEach(label -> System.out.print(label + " "));
        System.out.println();

        //Question 2
        System.out.println("Enter the label of team to display team players:");
        String label = sc.nextLine();

        List<PlayerDTO> playerDTOList = iplStatsService.getPlayersByTeam(label);
        System.out.println("The team players are:");
        playerDTOList.forEach(player -> System.out.println("Name : " + player.getName() + " Role: " + player.getRole()));

        System.out.println();

//        Question 3

        System.out.println("Enter the label of team to display count Of role:");
        String labelRole = sc.nextLine();
        List<RoleCountDTO> roleCountDTOS = iplStatsService.getCountByRole(labelRole);
        roleCountDTOS.forEach(roleCountDTO -> System.out.println(roleCountDTO.getRoleName() + ":" + roleCountDTO.getCount()));
        System.out.println();

        // Question 4
        System.out.println("Enter the label of team and role of players to be displayed:");
        System.out.println("Enter the label of team:");
        String label2 = sc.nextLine();
        System.out.println("Enter the role of players:");
        String Role = sc.nextLine();
        List<PlayerDTO> playerDTOS = iplStatsService.getPlayersByTeam(Role, label2);
        playerDTOS.forEach(player -> System.out.println("Name : " + player.getName() + " Price: " + player.getPrize()));
        System.out.println();

        //Question 5
        System.out.println("Displaying all team details:");
        List<TeamDTO> teamDTOS = iplStatsService.getAllTeamDetails();
        teamDTOS.forEach(team -> System.out.println("Name : " + team.getName() + " Label: " + team.getLabel() + " Coach: " + team.getCoach() + " City : " + team.getCity() + "Home:" + team.getHome()));
        System.out.println();

        //Question 6

        System.out.println("Total amount spent by team:");
        List<TeamAmountDTO> teamAmountDTOS = iplStatsService.getTotalAmountSpentByTeam();
        teamAmountDTOS.forEach(team -> System.out.println("Team Label : " + team.getLabel() + " Total amount spent ; " + team.getAmount()));
        System.out.println();

        //Question 7
        System.out.println("Enter the role of player and label of team to display total amount spent:");
        System.out.println("Enter the role of player:");
        String roleAmount = sc.nextLine();
        System.out.println("Enter the label of team:");
        String labelAmount = sc.nextLine();
        iplStatsService.getTeamAmountByRole(labelAmount, roleAmount);
        System.out.println();

        //Question 8
        System.out.println("Enter the field name of player details to be sorted :");
        String fieldName = sc.next();

        List<PlayerDTO> playerDTOS1 = iplStatsService.getPlayersBySort(fieldName);
        for (PlayerDTO player : playerDTOS1) {
            System.out.println("Name : " + player.getName() + " Role :" + player.getRole() + " Price: " + player.getPrize());
        }
        System.out.println();

        //Question 9

        Map<String, List<PlayerDTO>> maxPaidPlayerByRole = iplStatsService.getMaxPaidPlayersByRole();
        System.out.println("Maximum Paid Player by role: ");
        maxPaidPlayerByRole.entrySet().stream()
                .forEach(entry -> {
                    System.out.print("Role: " + entry.getKey());
                    entry.getValue()
                            .forEach(player -> System.out.println(" Name: " + player.getName() + ", Price: " + player.getPrize()));
                });

    }
}
