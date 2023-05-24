package Collection;

import Exceptions.WrongField;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvRecurse;

import java.io.Serializable;

/**
 * Person class. Used to save Dragon killer.
 * @author Alexander Ivanov
 */
public class Person implements Serializable {
    @CsvBindByName(column = "keller_name", required = true)
    private String name; // Value can not be null, String can not be empty
    @CsvBindByName(column = "passport_id", required = true)
    private String passportID; // String cannot be empty, Field can be null, Length shouldn't be greater than 28 and less than 8
    @CsvBindByName(column = "hair_color", required = false)
    private Color hairColor; // Value can be null
    @CsvRecurse
    private Location location; // Value can be null

    public Person(){

    }

    public String getName() {
        return name;
    }

    public String getPassportID() {
        return this.passportID;
    }

    public Color getHairColor() {
        return this.hairColor;
    }

    public Location getLocation() {
        return this.location;
    }

    /**
     * Set Person name.
     * @param name name to set.
     * @throws WrongField when value is null or empty string.
     */
    public void setName(String name) throws WrongField{
        if(name == null) throw new WrongField("Name can nor be null");
        if(name.length() == 0) throw new WrongField("String can not be empty");
        this.name = name;
    }

    /**
     * Set Person passport ID.
     * @param passportID value to set.
     * @throws WrongField when value is null or length greater than 28 or less than 8.
     */
    public void setPassportID(String passportID) throws WrongField{
        if(passportID == null) throw new WrongField("PassportID can nor be null");
        if(passportID.length() < 8 || passportID.length() > 28) throw new WrongField("PassportID length shouldn't be greater than 28 and less than 8");
        this.passportID = passportID;
    }

    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Person{name=" + name +
                ", passportID=" + passportID +
                ", hairColor=" + hairColor +
                ", Location=" + location + "}";
    }
}
