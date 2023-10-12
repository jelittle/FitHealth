package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//build an abstract connector class to connect to different databases

/**
 * Created by Joshua Little
 * Connects to the database
 */
class Connector {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/eecs3311";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "asdasd";

    public Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            System.out.println("Successfully connected to the database.");
        } catch (SQLException e) {
            System.out.println("Error while connecting to the database.");
            e.printStackTrace();
        }
        return connection;
    }
}


