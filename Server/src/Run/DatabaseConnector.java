package Run;

import Collection.*;

import java.sql.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

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
        Statement statement = this.connection.createStatement();

        String sql_command = String.format("INSERT INTO coordinates (x, y) VALUES (%f, %d)", coordinates.getX(), coordinates.getY());
        statement.executeUpdate(sql_command);
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
        Statement statement = this.connection.createStatement();

        String sql_command = String.format("SELECT * FROM coordinates WHERE id = %d", id);
        ResultSet resultSet = statement.executeQuery(sql_command);
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
        Statement statement = this.connection.createStatement();

        String sql_command = String.format("SELECT * FROM colors WHERE id = %d", id);
        ResultSet resultSet = statement.executeQuery(sql_command);
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
        Statement statement = this.connection.createStatement();

        String sql_command = String.format("SELECT * FROM characters WHERE id = %d", id);
        ResultSet resultSet = statement.executeQuery(sql_command);
        this.connection.commit();

        resultSet.next();

        return DragonCharacter.valueOf(resultSet.getString("character"));
    }

    /**
     * Get DragonCharacter id in database by instance
     * @param dragonCharacter instance to find
     * @return id
     */
    public int getCharacterId(DragonCharacter dragonCharacter) throws SQLException {
        Statement statement = this.connection.createStatement();

        String sql_command = String.format("SELECT id FROM characters WHERE character = '%s'", dragonCharacter.toString());
        ResultSet resultSet = statement.executeQuery(sql_command);
        this.connection.commit();

        resultSet.next();

        return resultSet.getInt("id");
    }

    /**
     * Get Color id in database by instance
     * @param color instance to find
     * @return id
     */
    public int getColorID(Color color) throws SQLException {
        Statement statement = this.connection.createStatement();

        String sql_command = String.format("SELECT id FROM colors WHERE color_name = '%s'", color.toString());
        ResultSet resultSet = statement.executeQuery(sql_command);
        this.connection.commit();

        resultSet.next();

        return resultSet.getInt("id");
    }

    /**
     * Adds location instance to database and returns id of added element
     * @param location instance to add
     * @return id of added element
     * @throws SQLException when connection issues
     */
    public int addLocation(Location location) throws SQLException {
        Statement statement = this.connection.createStatement();

        String sql_command = String.format("INSERT INTO locations (x, y, name) VALUES (%f, %d, '%s')", location.getX(), location.getY(), location.getName());
        statement.executeUpdate(sql_command);
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
        Statement statement = this.connection.createStatement();

        String sql_command = String.format("SELECT * FROM locations WHERE id = %d", id);
        ResultSet resultSet = statement.executeQuery(sql_command);
        this.connection.commit();

        resultSet.next();

        Location location = new Location();
        location.setX(resultSet.getFloat("x"));
        location.setY(resultSet.getInt("y"));
        location.setName(resultSet.getString("name"));

        return location;
    }

    /**
     * Adds person instance to database and returns id of added element
     * @param person instance to add
     * @return id of added element
     * @throws SQLException when connection issues
     */
    public int addPerson(Person person) throws SQLException {
        Statement statement = this.connection.createStatement();

        int location_id = this.addLocation(person.getLocation());
        int color_id = this.getColorID(person.getHairColor());

        String sql_command = String.format("INSERT INTO persons (name, passport_id, hair_color_id, location_id) VALUES ('%s', '%s', %d, %d)", person.getName(), person.getPassportID(), color_id, location_id);
        statement.executeUpdate(sql_command);
        this.connection.commit();
        return getId("persons");
    }

    /**
     * Read person object with spec. id from database
     * @param id person id to read
     * @return Person instance
     * @throws SQLException when connection issues
     */
    public Person readPerson(int id) throws SQLException {
        Statement statement = this.connection.createStatement();

        String sql_command = String.format("SELECT * FROM persons WHERE id = %d", id);
        ResultSet resultSet = statement.executeQuery(sql_command);
        this.connection.commit();

        resultSet.next();

        Person person = new Person();
        person.setLocation(this.readLocation(resultSet.getInt("location_id")));
        person.setHairColor(this.readColor(resultSet.getInt("hair_color_id")));
        person.setName(resultSet.getString("name"));
        person.setPassportID(resultSet.getString("passport_id"));

        return person;
    }

    /**
     * Read dragon object with spec. id from database
     * @param id dragon id to read
     * @return Dragon instance
     * @throws SQLException when connection issues
     */
    public Dragon readDragon(long id) throws SQLException {
        Statement statement = this.connection.createStatement();

        String sql_command = String.format("SELECT * FROM dragons WHERE id = %d", id);
        ResultSet resultSet = statement.executeQuery(sql_command);
        this.connection.commit();

        resultSet.next();

        Dragon dragon = new Dragon();
        dragon.setId(resultSet.getLong("id"));
        dragon.setName(resultSet.getString("name"));
        dragon.setCoordinates(this.readCoordinates(resultSet.getInt("coordinates_id")));
        dragon.setCreationDate(resultSet.getDate("creation_date").toLocalDate().atStartOfDay( ZoneId.of( "Europe/Moscow")));
        dragon.setAge(resultSet.getLong("age"));
        dragon.setDescription(resultSet.getString("description"));
        dragon.setWeight(resultSet.getDouble("weight"));
        dragon.setCharacter(this.readDragonCharacter(resultSet.getInt("character_id")));
        dragon.setKiller(this.readPerson(resultSet.getInt("killer_id")));
        return dragon;
    }

    /**
     * Adds dragon instance to database and returns id of added element
     * @param dragon instance to add
     * @return id of added element
     * @throws SQLException when connection issues
     */
    public int addDragon(Dragon dragon) throws SQLException {
        Statement statement = this.connection.createStatement();

        int coordinatesId = this.addCoordinates(dragon.getCoordinates());
        int characterId = this.getCharacterId(dragon.getCharacter());
        int killerId = this.addPerson(dragon.getKiller());

        String sql_command = String.format("INSERT INTO dragons (name, coordinates_id, creation_date, age, description, weight, character_id, killer_id) " +
                "VALUES ('%s', %d, '%s', %d, '%s', %f, %d, %d)", dragon.getName(), coordinatesId, dragon.getCreationDate().toLocalDate(), dragon.getAge(), dragon.getDescription(), dragon.getWeight(), characterId, killerId);
        statement.executeUpdate(sql_command);
        this.connection.commit();
        return getId("dragons");
    }


    /**
     * Get id of last added element
     * @return d of last added element
     * @throws SQLException when connection issues
     */
    private int getId(String table) throws SQLException {
        Statement statement = this.connection.createStatement();

        String sql_command = String.format("SELECT currval(pg_get_serial_sequence('%s', 'id'))", table);
        ResultSet resultSet = statement.executeQuery(sql_command);
        resultSet.next();
        int id = resultSet.getInt("currval");
        this.connection.commit();
        return id;
    }

    /**
     * Clear database method. Drops all data in all the tables
     * @throws SQLException when connection issues
     */
    public void clearDatabase() throws SQLException {
        Statement statement = this.connection.createStatement();


        String sql_command = "DELETE FROM dragons";
        statement.executeUpdate(sql_command);

        sql_command = "DELETE FROM persons";
        statement.executeUpdate(sql_command);

        sql_command = "DELETE FROM coordinates";
        statement.executeUpdate(sql_command);

        sql_command = "DELETE FROM locations";
        statement.executeUpdate(sql_command);

        this.connection.commit();

        System.out.println("Collection cleared");
    }

    /**
     * Get all the dragons id's in databse
     * @return id's ArrayList
     * @throws SQLException when connection issues
     */
    public ArrayList<Long> getDragonsIDs() throws SQLException {
        Statement statement = this.connection.createStatement();

        String sql_command = "SELECT id FROM dragons";
        ResultSet resultSet = statement.executeQuery(sql_command);
        this.connection.commit();

        ArrayList<Long> ids = new ArrayList<Long>();

        while (resultSet.next()) {
            ids.add(resultSet.getLong("id"));
        }

        return ids;
    }

    public void removeDragonByID(long id) throws SQLException {
        Statement statement = this.connection.createStatement();

        String sql_command = String.format("DELETE FROM dragons WHERE id = %d", id);
        statement.executeUpdate(sql_command);
        connection.commit();

    }

}
