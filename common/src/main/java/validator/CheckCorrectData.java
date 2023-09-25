package validator;

import data.*;

public class CheckCorrectData {
    public boolean checkID(String id) {
        try {
            Long ID = Long.parseLong(id);
            return true;
        }
        catch (IllegalArgumentException e) {
            System.out.println("ID должен быть числом");
            return false;
        }
    }


    public boolean checkCoordinateX(Number x) {
        return x != null;
    }

    public boolean checkCoordinateY(Integer y) {
        return y != null && y > Coordinates.getMinY();
    }

    public boolean checkName(String name) {
        return name != null && !name.equals("");
    }

    public boolean checkDistance(Integer distance) {
        return distance > Route.getMinDistance();
    }

    public boolean checkLocationCoordinateY(Double y) {
        return y != null;
    }

    public boolean checkLocationName(String name) {
        if (name != null) return !name.equals("");
        return true;
    }

    public boolean isNumber(String arg) {
        try {
            Integer.parseInt(arg);
            return true;
        }
        catch(NumberFormatException e) {
            System.out.println("Данный аргумент не является числом");
        }
        return false;
    }

}
