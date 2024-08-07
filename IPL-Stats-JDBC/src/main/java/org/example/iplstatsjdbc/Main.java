package org.example.iplstatsjdbc;

import org.example.iplstatsjdbc.domain.Team;
import org.example.iplstatsjdbc.service.ConnectionUtil;
import org.example.iplstatsjdbc.service.IPLJDBCServiceImpl;
import org.example.iplstatsjdbc.service.JsonReaderUtil;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
         List<Team> teamList = JsonReaderUtil.readTeams();
        ConnectionUtil connectionUtil = new ConnectionUtil();
        connectionUtil.dbSetup();

        connectionUtil.insertData(teamList);


        // Insert data

        Scanner scanner = new Scanner(System.in);
        IPLJDBCServiceImpl ipljdbcService = new IPLJDBCServiceImpl();
        try{
            while (true) {
                System.out.println("Choose an option:");
                System.out.println("1. Get all team details");
                System.out.println("2. Get amount spent on a role by team");
                System.out.println("3. Get amount spent by each team");
                System.out.println("4. Get count by role for a team");
                System.out.println("5. Get max paid players by role");
                System.out.println("6. Get players by team");
                System.out.println("7. Get players by team and role");
                System.out.println("8. Get players sorted by field");
                System.out.println("9. List all team labels");
                System.out.println("0. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        ipljdbcService.getAllTeamDetails();
                        break;

                    case 2:
                        System.out.print("Enter team label: ");
                        String label2 = scanner.nextLine();
                        System.out.print("Enter role: ");
                        String role = scanner.nextLine();
                        ipljdbcService.getTeamAmountByRole(label2,role);
                        break;

                    case 3:
                        ipljdbcService.getTotalAmountSpentByTeam();
                        break;

                    case 4:
                        System.out.print("Enter team label: ");
                        String label4 = scanner.nextLine();
                        ipljdbcService.getCountByRole(label4);
                        break;

                    case 5:
                        ipljdbcService.getMaxPaidPlayersByRole();
                        break;

                    case 6:
                        System.out.print("Enter team label: ");
                        String label6 = scanner.nextLine();
                        ipljdbcService.getPlayersByTeam(label6);
                        break;

                    case 7:
                        System.out.print("Enter team label: ");
                        String label7 = scanner.nextLine();
                        System.out.print("Enter role: ");
                        String role7 = scanner.nextLine();

                        ipljdbcService.getPlayersByTeam(role7,label7);
                        break;

                    case 8:
                        System.out.print("Enter field name to sort by: ");
                        String fieldName = scanner.nextLine();

                        ipljdbcService.getPlayersBySort(fieldName);
                        break;

                    case 9:
                       ipljdbcService.getTeamLabels();
                        break;

                    case 0:
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid choice, please try again.");
                }

            }}
        finally {
            // Ensure the connection is closed
            connectionUtil.closeConn();
            scanner.close();
        }
    }
}
