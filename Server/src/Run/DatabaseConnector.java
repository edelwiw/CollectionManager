package Run;

import Collection.*;

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
        return getId("coordinates");
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
     * Read color object with spec. id from database
     * @param id color id to read
     * @return Color instance
     * @throws SQLException when connection issues
     */
    public Color readColor(int id) throws SQLException {
        Statement insert = this.connection.createStatement();

        String sql_command = String.format("SELECT * FROM colors WHERE id = %d", id);
        ResultSet resultSet = insert.executeQuery(sql_command);
        this.connection.commit();

        resultSet.next();

        return Color.valueOf(resultSet.getString("color_name"));

    }

    /**
     * Read DragonCharacter object with spec. id from database
     * @param id DragonCharacter id to read
     * @return DragonCharacter instance
     * @throws SQLException when connection issues
     */
    public DragonCharacter readDragonCharacter(int id) throws SQLException {
        Statement insert = this.connection.createStatement();

        String sql_command = String.format("SELECT * FROM characters WHERE id = %d", id);
        ResultSet resultSet = insert.executeQuery(sql_command);
        this.connection.commit();

        resultSet.next();

        return DragonCharacter.valueOf(resultSet.getString("character"));

    }

    /**
     * Adds location instance to database and returns id of added element
     * @param location instance to add
     * @return id of added element
     * @throws SQLException when connection issues
     */
    public int addLocation(Location location) throws SQLException {
        Statement insert = this.connection.createStatement();

        String sql_command = String.format("INSERT INTO locations (x, y, name) VALUES (%f, %d, %s)", location.getX(), location.getY(), location.getName());
        insert.executeUpdate(sql_command);
        this.connection.commit();
        return getId("locations");
    }

    /**
     * Read location object with spec. id from database
     * @param id location id to read
     * @return Location instance
     * @throws SQLException when connection issues
     */
    public Location readLocation(int id) throws SQLException {
        Statement insert = this.connection.createStatement();

        String sql_command = String.format("SELECT * FROM locations WHERE id = %d", id);
        ResultSet resultSet = insert.executeQuery(sql_command);
        this.connection.commit();

        resultSet.next();

        Location location = new Location();
        location.setX(resultSet.getFloat("x"));
        location.setY(resultSet.getInt("y"));
        location.setName(resultSet.getString("name"));

        return location;

    }


    /**
     * Get id of last added element
     * @return d of last added element
     * @throws SQLException when connection issues
     */
    private int getId(String table) throws SQLException {
        Statement insert = this.connection.createStatement();

        String sql_command = String.format("SELECT currval(pg_get_serial_sequence('%s', 'id'))", table);
        ResultSet resultSet = insert.executeQuery(sql_command);
        resultSet.next();
        int id = resultSet.getInt("currval");
        this.connection.commit();
        return id;
    }

}
