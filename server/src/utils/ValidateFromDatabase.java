package utils;

import data.Route;
import validator.CheckCorrectData;

public class ValidateFromDatabase {
    private CheckCorrectData check = new CheckCorrectData();
    public boolean validate(Route element) {
        return check.checkName(element.getName()) &&
                check.checkCoordinateX(element.getCoordinates().getX()) &&
                check.checkCoordinateY(element.getCoordinates().getY()) &&
                check.checkName(element.getFrom().getName()) &&
                check.checkCoordinateX(element.getFrom().getX()) &&
                check.checkLocationCoordinateY(element.getFrom().getY()) &&
                check.checkName(element.getTo().getName()) &&
                check.checkCoordinateX(element.getTo().getX()) &&
                check.checkLocationCoordinateY(element.getTo().getY()) &&
                check.checkDistance(element.getDistance());
    }
}
