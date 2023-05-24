package Collection;

import Exceptions.WrongField;
import com.opencsv.bean.CsvBindByName;

/**
 * Location class. Used to store Person coordinates.
 * @author alexander Ivanov @edelwiw
 */
public class Location {
    @CsvBindByName(column = "location_x", required = true)
    private float x;
    @CsvBindByName(column = "location_y", required = true)
    private int y;
    @CsvBindByName(column = "location_name", required = false)
    private String name; // Value cannot be greater than 535, Value can be null

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

    /**
     * Set X coordinate.
     * @param x coordinate to set.
     * @throws WrongField when value os null.
     */
    public void setX(Float x) throws WrongField{
        if(x == null) throw new WrongField("X can not be null");
        this.x = x;
    }

    /**
     * Set Y coordinate.
     * @param y coordinate to set.
     * @throws WrongField when value is null.
     */
    public void setY(Integer y) throws WrongField{
        if(y == null) throw new WrongField("Y can not be null");
        this.y = y;
    }

    /**
     * Set name or location.
     * @param name name to set.
     * @throws WrongField when value is null of length greater than 535.
     */
    public void setName(String name) throws WrongField {
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
