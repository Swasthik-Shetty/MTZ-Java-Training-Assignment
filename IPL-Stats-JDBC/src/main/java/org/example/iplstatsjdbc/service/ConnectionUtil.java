package org.example.iplstatsjdbc.service;

import org.example.iplstatsjdbc.domain.Player;
import org.example.iplstatsjdbc.domain.Team;
import org.postgresql.Driver;

import java.sql.*;
import java.util.List;

public class ConnectionUtil {

    private static final String url ="jdbc:postgresql://dpg-cqorlp2j1k6c73d7arjg-a.oregon-postgres.render.com:5432/mtz_pgsql_assignment";
    private static final String user ="mtz_pgsql_assignment_user";
    private static final String password = "QuchEQA1KZz32MKveO6L4CopPRHi4po1";
    private Connection conn=null;


    public Connection connection(){
        try {
            conn = DriverManager.getConnection(url,user,password);

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public void dbSetup() {
        try  {
            conn=connection();
            Statement stmt = conn.createStatement();
            // Check if the 'teams' table exists
            if (!tableExists(conn, "teams")) {
                // Create tables
                String createTeamsTable = "CREATE TABLE teams (" +
                        "id SERIAL PRIMARY KEY," +
                        "name VARCHAR(255) UNIQUE," +
                        "city VARCHAR(255)," +
                        "coach VARCHAR(255)," +
                        "home VARCHAR(255)," +
                        "label VARCHAR(50)" +
                        ")";
                stmt.executeUpdate(createTeamsTable);

                String createPlayersTable = "CREATE TABLE players (" +
                        "id SERIAL PRIMARY KEY," +
                        "team_name VARCHAR(255)," +
                        "name VARCHAR(255)," +
                        "price DOUBLE PRECISION," +
                        "role VARCHAR(50)," +
                        "FOREIGN KEY (team_name) REFERENCES teams(name)" +
                        ")";
                stmt.executeUpdate(createPlayersTable);

                System.out.println("Tables created successfully.");
            } else {
                System.out.println("Tables already exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private boolean tableExists(Connection conn, String tableName) throws SQLException {
        DatabaseMetaData meta = conn.getMetaData();
        try (ResultSet rs = meta.getTables(null, null, tableName, null)) {
            return rs.next();
        }
    }

    public void insertData(List<Team> teams) {
        try  {
            conn=connection();
            Statement stmt = conn.createStatement();
            if (isTableEmpty(conn, "teams")) {
                String teamSql = "INSERT INTO teams (home, label, name, coach, city) VALUES (?, ?, ?, ?, ?) ON CONFLICT (name) DO NOTHING";
                try (PreparedStatement teamStmt = conn.prepareStatement(teamSql)) {
                    for (Team team : teams) {
                        teamStmt.setString(1, team.getHome());
                        teamStmt.setString(2, team.getLabel());
                        teamStmt.setString(3, team.getName());
                        teamStmt.setString(4, team.getCoach());
                        teamStmt.setString(5, team.getCity());
                        teamStmt.addBatch();
                    }
                    teamStmt.executeBatch();
                }
            }

            if (isTableEmpty(conn, "players")) {
                String playerSql = "INSERT INTO players (team_name, role, name, price) VALUES (?, ?, ?, ?) ON CONFLICT (team_name, name) DO NOTHING";
                try (PreparedStatement playerStmt = conn.prepareStatement(playerSql)) {
                    for (Team team : teams) {
                        for (Player player : team.getPlayers()) {
                            playerStmt.setString(1, team.getName());
                            playerStmt.setString(2, player.getRole());
                            playerStmt.setString(3, player.getName());
                            playerStmt.setDouble(4, player.getPrice());
                            playerStmt.addBatch();
                        }
                    }
                    playerStmt.executeBatch();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private boolean isTableEmpty(Connection connection, String tableName) throws SQLException {
        String query = "SELECT COUNT(*) FROM " + tableName;
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
        }
        return false;
    }
    public void closeConn(){
        try {
            conn.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

}