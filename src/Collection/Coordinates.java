package Collection;

import Exceptions.WrongField;

public class Coordinates {
    private double x; // Value can not be greater than 710
    private Long y; // Value cannot be null

    public Coordinates(double x, Long y){
        this.setX(x);
        this.setY(y);
    }

    public double getX() {
        return this.x;
    }

    public Long getY() {
        return this.y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(Long y) {
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

}