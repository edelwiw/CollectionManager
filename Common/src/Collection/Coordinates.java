package Collection;

import Exceptions.WrongField;
import com.opencsv.bean.CsvBindByName;

import java.io.Serializable;

/**
 * Coordinates class. Used to store dragon coordinates.
 * @author Alexander Ivanov @edelwiw
 */
public class Coordinates implements Serializable {
    @CsvBindByName(column = "coordinates_x", required = true)
    private double x; // Value can not be greater than 710
    @CsvBindByName(column = "coordinates_y", required = true)
    private Long y; // Value cannot be null

    public Coordinates(){
    }

    public double getX() {
        return this.x;
    }

    public Long getY() {
        return this.y;
    }

    /**
     * Set X coordinate. Value can't be null.
     * @param x coordinate X to set.
     * @throws WrongField when value is null or value greater than 710.
     */
    public void setX(Double x) throws WrongField {
        if(x == null) throw new WrongField("X can not be null");
        if(x > 710) throw new WrongField("X can not be greater than 710");
        this.x = x;
    }

    /**
     * Sey Y coordinate. Value can't be null.
     * @param y coordinate Y to set.
     * @throws WrongField when value is null.
     */
    public void setY(Long y) throws WrongField{
        if(y == null) throw new WrongField("Y can not be null");
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + this.x +
                ", y=" + this.y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Double.compare(that.x, x) == 0 && y.equals(that.y);
    }

}