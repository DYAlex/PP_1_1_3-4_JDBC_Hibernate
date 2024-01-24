package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/pp_1_1_3_JDBC";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "23102310";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    DB_URL,
                    DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Connection ERROR: " + e.getMessage());
        }
        return connection;
    }
}
