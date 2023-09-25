package data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Coordinates implements Serializable {
    private long x;
    private Integer y;

    private static final Integer MIN_Y = -807;

    public static Integer getMinY() {
        return MIN_Y;
    }



    @Override
    public String toString() {
        return "(" + x + "; " + y + ")";
    }
}
