package Run;

import Collection.Coordinates;

import java.sql.*;

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
            connection.setAutoCommit(false);
        } else {
             throw new SQLException("Failed to make connection to database");
        }
    }

    public int addCoordinates(Coordinates coordinates) throws SQLException {
        Statement insert = this.connection.createStatement();

        String sql_command = String.format("INSERT INTO coordinates (x, y) VALUES (%f, %d)", coordinates.getX(), coordinates.getY());
        insert.executeUpdate(sql_command);
        this.connection.commit();
        return getId();
    }


    private int getId() throws SQLException {
        Statement insert = this.connection.createStatement();

        String sql_command = "SELECT currval(pg_get_serial_sequence('coordinates','id'));";
        ResultSet resultSet = insert.executeQuery(sql_command);
        resultSet.next();
        int id = resultSet.getInt("currval");
        this.connection.commit();
        return id;
    }

}
