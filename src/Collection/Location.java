package Collection;

import Exceptions.WrongField;

public class Location {
    private float x;
    private int y;
    private String name; // Value cannot be greater than 535, Value cannot be null

    public Location(float x, int y, String name) {
        this.setX(x);
        this.setY(y);
        this.setName(name);
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

    public void setX(float x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setName(String name) {
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
