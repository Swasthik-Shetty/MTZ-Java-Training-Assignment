package org.example.iplstatsjdbc.service;

import org.example.iplstatsjdbc.domain.Player;
import org.example.iplstatsjdbc.domain.Team;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class ConnectionUtil {

    private Connection conn=null;


    public Connection connection(){
        try {
            Properties prop = new Properties();
            String propFileName = "db.properties";

            InputStream file = ConnectionUtil.class.getClassLoader().getResourceAsStream(propFileName);
            prop.load(file);
            file.close();

            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");

            conn = DriverManager.getConnection(url,user,password);
        }
        catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void dbSetup() {
        try  {
            conn=connection();
            Statement stmt = conn.createStatement();


                // Create tables
                String createTeamsTable = "CREATE TABLE IF NOT EXISTS teams  (" +
                        "id SERIAL PRIMARY KEY," +
                        "name VARCHAR(255) UNIQUE," +
                        "city VARCHAR(255)," +
                        "coach VARCHAR(255)," +
                        "home VARCHAR(255)," +
                        "label VARCHAR(50)" +
                        ")";
                stmt.executeUpdate(createTeamsTable);

                String createPlayersTable = "CREATE TABLE IF NOT EXISTS players (" +
                        "id SERIAL PRIMARY KEY," +
                        "team_name VARCHAR(255)," +
                        "name VARCHAR(255)," +
                        "price DOUBLE PRECISION," +
                        "role VARCHAR(50)," +
                        "FOREIGN KEY (team_name) REFERENCES teams(name)" +
                        ")";
                stmt.executeUpdate(createPlayersTable);

                System.out.println("Tables created successfully!!");
                stmt.close();
            closeConn();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insertData() {
        try  {
            conn=connection();
            Statement stmt = conn.createStatement();

            List<Team> teamList = JsonReaderUtil.readTeams();

            if (isTableEmpty(conn, "teams")) {
                String teamSql = "INSERT INTO teams (home, label, name, coach, city) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement teamStmt = conn.prepareStatement(teamSql)) {
                    for (Team team : teamList) {
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
                String playerSql = "INSERT INTO players (team_name, role, name, price) VALUES (?, ?, ?, ?)";
                try (PreparedStatement playerStmt = conn.prepareStatement(playerSql)) {
                    for (Team team : teamList) {
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
            closeConn();
            System.out.println("Teams and Players Table Details entered successfully!!");

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