package Collection;

import Collection.Color;
import Collection.Location;
import Exceptions.WrongField;

public class Person {
    private String name; // Value can not be null, String can not be empty
    private String passportID; // String cannot be empty, Field can be null, Length shouldn't be greater than 28 and less than 8
    private Color hairColor; // Value can be null
    private Location location; // Value can be null

    public Person(String name, String passportID, Color hairColor, Location location) {
        this.setName(name);
        this.setPassportID(passportID);
        this.setLocation(location);
        this.setHairColor(hairColor);
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

    public void setName(String name) {
        if(name == null) throw new WrongField("Name can nor be null");
        if(name.length() == 0) throw new WrongField("String can not be empty");
        this.name = name;
    }

    public void setPassportID(String passportID) {
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
        return "name=" + name + " " +
                ", passportID=" + passportID + " " +
                ", hairColor=" + hairColor + " " +
                ", Collection.Location=" + location;
    }
}