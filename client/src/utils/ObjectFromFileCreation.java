package utils;

import data.Coordinates;
import data.Location;
import data.Route;
import validator.CheckCorrectData;

public class ObjectFromFileCreation {
    private String name;
    private long routeX;
    private Integer routeY;

    private String locationNameFrom;
    private Double fromLocationX;
    private Double fromLocationY;

    private String locationNameTo;
    private Double toLocationX;
    private Double toLocationY;
    private int distance;

    private CheckCorrectData check = new CheckCorrectData();

    public Route createElement() {
        return new Route(name, new Coordinates(routeX, routeY),
                new Location(locationNameFrom, fromLocationX, fromLocationY),
                new Location(locationNameTo, toLocationX, toLocationY),
                distance
        );
    }

    public Route createObject(String[] args) {

        if (args.length != 10) {
            System.out.println("Количество параметров не равно 10! Проверьте файл.");
            return null;
        }

        if (refactor(args)) {
            return createElement();
        }

        return null;
    }

    private boolean refactor(String[] args) {
        int countValid = 0;

        if (check.checkName(args[0])) {
            name = args[0];
            countValid++;
        }
        if (check.isNumber(args[1]) && check.checkCoordinateX(Long.parseLong(args[1]))) {
            routeX = Long.parseLong(args[1]);
            countValid++;
        }
        if (check.isNumber(args[2]) && check.checkCoordinateY(Integer.parseInt(args[2]))) {
            routeY = Integer.parseInt(args[2]);
            countValid++;
        }
        if (check.checkLocationName(args[3])) {
            locationNameFrom = args[3];
            countValid++;
        }
        if (check.isNumber(args[4]) && check.checkCoordinateX(Double.parseDouble(args[4]))) {
            fromLocationX = Double.parseDouble(args[4]);
            countValid++;
        }
        if (check.isNumber(args[5]) && check.checkLocationCoordinateY(Double.parseDouble(args[5]))) {
            fromLocationY = Double.parseDouble(args[5]);
            countValid++;
        }

        if (check.checkLocationName(args[6])) {
            locationNameTo = args[6];
            countValid++;
        }
        if (check.isNumber(args[7]) && check.checkCoordinateX(Double.parseDouble(args[7]))) {
            toLocationX = Double.parseDouble(args[7]);
            countValid++;
        }
        if (check.isNumber(args[8]) && check.checkLocationCoordinateY(Double.parseDouble(args[8]))) {
            toLocationY = Double.parseDouble(args[8]);
            countValid++;
        }

        if (check.isNumber(args[9]) && check.checkDistance(Integer.parseInt(args[9]))) {
            distance = Integer.parseInt(args[9]);
            countValid++;
        }

        return countValid == 10;
    }
}
