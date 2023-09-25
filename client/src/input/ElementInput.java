package input;

import data.Coordinates;
import java.util.Scanner;
import validator.CheckCorrectData;
import data.Location;
import data.Route;

public class ElementInput {
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

    private CheckCorrectData check;


    public ElementInput() {
        this.check = new CheckCorrectData();
    }

    public void nameInput() {
        while (true) {
            final Scanner input = new Scanner(System.in);
            System.out.print("Введите имя пути: ");
            name = input.nextLine().trim();
            if (check.checkName(name)) break;
            System.out.println("Неправильный ввод имени.");
        }
    }

    public void xRouteInput() {
        while (true) {
            final Scanner input = new Scanner(System.in);
            System.out.print("Введите координату x вашего местонахождения: ");
            if (input.hasNextLong()) {
                routeX = input.nextLong();
                if (check.checkCoordinateX(routeX)) break;
            }
            System.out.println("Неправильный ввод координаты x.");
        }
    }

    public void yRouteInput() {
        while (true) {
            final Scanner input = new Scanner(System.in);
            System.out.print("Введите координату y вашего местонахождения: ");
            if (input.hasNextInt()) {
                routeY = input.nextInt();
                if (check.checkCoordinateY(routeY)) break;
            }
            System.out.println("Неправильный ввод координаты y.");
        }
    }

    public void locationNameFromInput() {
        while (true) {
            final Scanner input = new Scanner(System.in);
            System.out.print("Введите имя начала пути: ");
            locationNameFrom = input.nextLine().trim();
            if (check.checkLocationName(locationNameFrom)) break;
            System.out.println("Неправильный ввод имени начала пути.");
        }
    }

    public void xLocationFromInput() {
        while (true) {
            final Scanner input = new Scanner(System.in);
            System.out.print("Введите координату x начала пути: ");
            if (input.hasNextDouble()) {
                fromLocationX = input.nextDouble();
                if (check.checkCoordinateX(fromLocationX)) break;
            }
            System.out.println("Неправильный ввод координаты x начала пути.");
        }
    }


    public void yLocationFromInput() {
        while (true) {
            final Scanner input = new Scanner(System.in);
            System.out.print("Введите координату y начала пути: ");
            if (input.hasNextDouble()) {
                fromLocationY = input.nextDouble();
                if (check.checkLocationCoordinateY(fromLocationY)) break;
            }
            System.out.println("Неправильный ввод координаты y начала пути.");
        }
    }



    public void locationNameToInput() {
        while (true) {
            final Scanner input = new Scanner(System.in);
            System.out.print("Введите имя конца пути: ");
            locationNameTo = input.nextLine().trim();
            if (check.checkLocationName(locationNameTo)) break;
            System.out.println("Неправильный ввод имени конца пути.");
        }
    }

    public void xLocationToInput() {
        while (true) {
            final Scanner input = new Scanner(System.in);
            System.out.print("Введите координату x конца пути: ");
            if (input.hasNextDouble()) {
                toLocationX = input.nextDouble();
                if (check.checkCoordinateX(toLocationX)) break;
            }
            System.out.println("Неправильный ввод координаты x конца пути.");
        }
    }


    public void yLocationToInput() {
        while (true) {
            final Scanner input = new Scanner(System.in);
            System.out.print("Введите координату y конца пути: ");
            if (input.hasNextDouble()) {
                toLocationY = input.nextDouble();
                if (check.checkLocationCoordinateY(toLocationY)) break;
            }
            System.out.println("Неправильный ввод координаты y конца пути.");
        }
    }

    public void distanceInput() {
        while (true) {
            final Scanner input = new Scanner(System.in);
            System.out.print("Введите длину пути: ");
            if (input.hasNextInt()) {
                distance = input.nextInt();
                if (check.checkDistance(distance)) break;
            }
            System.out.println("Неправильный ввод длины пути пути.");
        }
    }

    public Route createElement() {
        return new Route(name, new Coordinates(routeX, routeY),
               new Location(locationNameFrom, fromLocationX, fromLocationY),
               new Location(locationNameTo, toLocationX, toLocationY),
               distance
        );
    }

    public Route resultElement() {
        nameInput();
        xRouteInput();
        yRouteInput();
        locationNameFromInput();
        xLocationFromInput();
        yLocationFromInput();
        locationNameToInput();
        xLocationToInput();
        yLocationToInput();
        distanceInput();
        return createElement();
    }
}