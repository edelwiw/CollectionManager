package Collection;

import Collection.Color;
import Collection.Location;

public class Person {
    private String name; // Value can not be null, String can not be empty
    private String passportID; // String cannot be empty, Field can be null, Length shouldn't be greater than 28 and less than 8
    private Color hairColor; // Value can be null
    private Location location; // Value can be null

    public Person(String name, String passportID, Color hairColor, Location location) {
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

}
