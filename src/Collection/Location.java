package Collection;

public class Location {
    private float x;
    private int y;
    private String name; // Value cannot be greater than 535, Value cannot be null

    public Location(float x, int y, String name) {
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

}
