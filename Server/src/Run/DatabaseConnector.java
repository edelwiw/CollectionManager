package Run;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private Connection connection;

    public DatabaseConnector(String host, String user, String pass) throws SQLException{

        try {
            this.connection = DriverManager.getConnection(host, user, pass);
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            throw new SQLException("Cannot connect");
        }

        if (connection != null) {
            System.out.println("Successfully connected to database");
        } else {
             throw new SQLException("Failed to make connection to database");
        }
    }

}
