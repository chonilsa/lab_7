package data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
public class Route implements Serializable, Comparable<Route> {
    private long id; // Значение поля должно быть больше 0, Значение этого поля должно быть уникальным,
    // Значение этого поля должно генерироваться автоматически
    private String name; // Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; // Поле не может быть null
    private Date creationDate; // Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Location from; // Поле может быть null
    private Location to; // Поле может быть null
    private int distance; // Значение поля дожно быть больше 1
    private String owner;

    private final static Integer MIN_DISTANCE = 1;

    public Route(String name, Coordinates coordinates, Location from, Location to, int distance) {
        id = 0L;
        creationDate = new Date();
        this.name = name;
        this.coordinates = coordinates;
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public static Integer getMinDistance() {
        return MIN_DISTANCE;
    }


    @Override
    public int compareTo(Route o) {
        return Integer.compare(this.distance, o.distance);
    }

    @Override
    public String toString() {
        return id + ": " + name + "\n" +
                "coordinates: " + coordinates + "\n" +
                "creationDate: " + creationDate + "\n" +
                "location from: " + from + "\n" +
                "location to: " + to + "\n" +
                "distance: " + distance + "\n" +
                "owner: " + owner;

    }

}

