package data;

import java.io.Serializable;

public class Location implements Serializable {
    private String name;
    private Double x;
    private Double y;

    public Location(String name, Double x, Double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return name + " (" + x + "; " + y + ")";
    }
}
