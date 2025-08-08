package model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/bank_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "adminpw";

    public static Connection getInstance() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to database");
        }
    }

}
