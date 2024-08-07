package org.example.iplstatsjdbc.service;

import org.example.iplstatsjdbc.domain.Team;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IPLJDBCServiceImpl implements IPLJDBCService {
    List<Team> teamDetails = JsonReaderUtil.readTeams();
    ConnectionUtil util = new ConnectionUtil();
    Connection connection = util.connection();


    @Override
    public void getAllTeamDetails() {
        String sql = "SELECT * FROM teams";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", City: " + rs.getString("city") + ", Coach: " + rs.getString("coach") + ", Home: " + rs.getString("home") + ", Label: " + rs.getString("label"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getMaxPaidPlayersByRole() {
        String sql = "SELECT role, name, MAX(price) AS max_price FROM players GROUP BY role, name";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            Map<String, String> roleToPlayerMap = new HashMap<>();
            while (rs.next()) {
                String role = rs.getString("role");
                String playerName = rs.getString("name");
                roleToPlayerMap.put(role, playerName + " ($" + rs.getDouble("max_price") + ")");
            }
            for (Map.Entry<String, String> entry : roleToPlayerMap.entrySet()) {
                System.out.println("Role: " + entry.getKey() + ", Max Paid Player: " + entry.getValue());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getPlayersByTeam(String role, String label) {
        String sql = "SELECT name FROM players WHERE team_name IN (SELECT name FROM teams WHERE label = ?) AND role = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, label);
            stmt.setString(2, role);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("Player: " + rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getCountByRole(String label) {
        String sql = "SELECT role, COUNT(*) AS role_count FROM players WHERE team_name IN (SELECT name FROM teams WHERE label = ?) GROUP BY role";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, label);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("Role: " + rs.getString("role") + ", Count: " + rs.getInt("role_count"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void getPlayersBySort(String fieldName) {
        String sql = "SELECT * FROM players ORDER BY " + fieldName;
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("Team Name: " + rs.getString("team_name") + ", Name: " + rs.getString("name") + ", Price: " + rs.getDouble("price") + ", Role: " + rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getTeamAmountByRole(String label, String role) {
        String sql = "SELECT team_name, SUM(price) AS total_amount FROM players WHERE team_name IN (SELECT name FROM teams WHERE label = ?) AND role = ? GROUP BY team_name";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, label);
            stmt.setString(2, role);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("Team: " + rs.getString("team_name") + ", Total Amount: " + rs.getDouble("total_amount"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getTotalAmountSpentByTeam() {
        String sql = "SELECT team_name, SUM(price) AS total_amount FROM players GROUP BY team_name";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("Team: " + rs.getString("team_name") + ", Total Amount Spent: " + rs.getDouble("total_amount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getTeamLabels() {
        String sql = "SELECT DISTINCT label FROM teams";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("Label: " + rs.getString("label"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getPlayersByTeam(String label) {
        String sql = "SELECT name FROM players WHERE team_name IN (SELECT name FROM teams WHERE label = ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, label);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("Player: " + rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
