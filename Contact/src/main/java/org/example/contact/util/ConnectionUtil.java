package org.example.contact.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionUtil {

    public static Connection getConnection() {

        Connection conn = null;
        try {
            Properties prop = new Properties();
            String propFileName = "db.properties";

            InputStream file = ConnectionUtil.class.getClassLoader().getResourceAsStream(propFileName);
            prop.load(file);
            file.close();

            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");

            conn = DriverManager.getConnection(url, user, password);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static Statement getStatement(Connection conn) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stmt;
    }

    public static void closeStatement(Statement stmt) {
        try {
            stmt.close();

        } catch (Exception e) {
            System.out.println();
        }
    }

    public static void closeConn(Connection conn) {
        try {
            conn.close();

        } catch (Exception e) {
            System.out.println();
        }
    }
}
