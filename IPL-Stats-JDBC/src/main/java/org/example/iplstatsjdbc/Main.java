package org.example.iplstatsjdbc;

import org.example.iplstatsjdbc.domain.Team;
import org.example.iplstatsjdbc.service.ConnectionUtil;
import org.example.iplstatsjdbc.service.IPLJDBCServiceImpl;
import org.example.iplstatsjdbc.service.JsonReaderUtil;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ConnectionUtil connectionUtil = new ConnectionUtil();
        connectionUtil.dbSetup();
        connectionUtil.insertData();


        Scanner scanner = new Scanner(System.in);
        IPLJDBCServiceImpl ipljdbcService = new IPLJDBCServiceImpl();


        System.out.println("1. Get all team details");
        ipljdbcService.getAllTeamDetails();
        System.out.println();

        System.out.println("2. Get amount spent on a role by team");
        System.out.print("Enter team label: ");
        String label2 = scanner.nextLine();
        System.out.print("Enter role: ");
        String role = scanner.nextLine();
        ipljdbcService.getTeamAmountByRole(label2, role);
        System.out.println();

        System.out.println("3. Get amount spent by each team");
        ipljdbcService.getTotalAmountSpentByTeam();
        System.out.println();

        System.out.println("4. Get count by role for a team");
        System.out.print("Enter team label: ");
        String label4 = scanner.nextLine();
        ipljdbcService.getCountByRole(label4);
        System.out.println();

        System.out.println("5. Get max paid players by role");
        ipljdbcService.getMaxPaidPlayersByRole();
        System.out.println();

        System.out.println("6. Get players by team");
        System.out.print("Enter team label: ");
        String label6 = scanner.nextLine();
        ipljdbcService.getPlayersByTeam(label6);
        System.out.println();

        System.out.println("7. Get players by team and role");
        System.out.print("Enter team label: ");
        String label7 = scanner.nextLine();
        System.out.print("Enter role: ");
        String role7 = scanner.nextLine();
        ipljdbcService.getPlayersByTeam(role7, label7);
        System.out.println();

        System.out.println("8. Get players sorted by field");
        System.out.print("Enter field name to sort by: ");
        String fieldName = scanner.nextLine();
        ipljdbcService.getPlayersBySort(fieldName);
        System.out.println();

        System.out.println("9. List all team labels");
        ipljdbcService.getTeamLabels();
        System.out.println();

        System.out.println("Closing Connection...");
        connectionUtil.closeConn();
        scanner.close();
        System.out.println(" Connection Closed");

    }
}
