package Run;

import Collection.Coordinates;

import java.sql.*;

public class DatabaseConnector {
    private Connection connection;

    /**
     * Create and check connection to database
     * @param host hoar URI
     * @param user username
     * @param pass password
     * @throws SQLException when connection issues
     */
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

    /**
     * Adds coordinates instance to database and returns id of added element
     * @param coordinates instance to add
     * @return id of added element
     * @throws SQLException when connection issues
     */
    public int addCoordinates(Coordinates coordinates) throws SQLException {
        Statement insert = this.connection.createStatement();

        String sql_command = String.format("INSERT INTO coordinates (x, y) VALUES (%f, %d)", coordinates.getX(), coordinates.getY());
        insert.executeUpdate(sql_command);
        this.connection.commit();
        return getId();
    }

    /**
     * Read Coordinates object with spec. id from database
     * @param id coordinates id to read
     * @return Coordinates instance
     * @throws SQLException when connection issues
     */
    public Coordinates readCoordinates(int id) throws SQLException {
        Statement insert = this.connection.createStatement();

        String sql_command = String.format("SELECT * FROM coordinates WHERE id = %d", id);
        ResultSet resultSet = insert.executeQuery(sql_command);
        this.connection.commit();

        resultSet.next();

        Coordinates coordinates = new Coordinates();
        coordinates.setX(resultSet.getDouble("x"));
        coordinates.setY(resultSet.getLong("y"));

        return coordinates;

    }

    /**
     * Get id of last added element
     * @return d of last added element
     * @throws SQLException when connection issues
     */
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
