package Collection;

import Exceptions.WrongField;

public class Location {
    private float x;
    private int y;
    private String name; // Value cannot be greater than 535, Value cannot be null

    public Location() {
    }

    public float getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public String getName() {
        return this.name;
    }

    public void setX(Float x) throws WrongField{
        if(x == null) throw new WrongField("X can not be null");
        this.x = x;
    }

    public void setY(Integer y) throws WrongField{
        if(y == null) throw new WrongField("Y can not be null");
        this.y = y;
    }

    public void setName(String name) throws WrongField {
        if(name == null) throw new WrongField("Name can not be null");
        if(name.length() > 535) throw new WrongField("Name length can't be greater than 535");
        this.name = name;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + this.x +
                ", y=" + this.y +
                ", name='" + this.name + '\'' +
                '}';
    }
}
