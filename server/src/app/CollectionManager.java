package app;

import data.Coordinates;
import data.Location;
import data.Route;
import database.DatabaseManager;
import utils.ValidateFromDatabase;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;


public class CollectionManager {
    private CopyOnWriteArrayList<Route> collection = new CopyOnWriteArrayList<>();
    private final DatabaseManager databaseManager = new DatabaseManager();
    ZonedDateTime initTime = ZonedDateTime.now();

    public String info() {
        return " - команда выполнена успешно. '\n' Тип - " + collection.getClass() + "\n"
                + "Количество элементов - " + collection.size() + "\n"
                + "Дата инициализации - " + initTime;
    }

    public boolean insertAtIndex(Route element, Integer index, String owner) {
        Long id = databaseManager.add(element, owner);
        if (id != null) {
            element.setId(id);
            collection.add(index, element);
            return true;
        }
        return false;
    }


    public void reorder() {
        Collections.sort(collection, Collections.reverseOrder());
    }

    public ArrayList<String> history(String user) {
        return CommandsList.getHistory(user);
    }

    public String countLessThanDistance(Integer distance) {
        int amount = 0;
        for(Route element: collection) {
            if (element.getDistance() < distance) amount++;
        }
        return "Количество элементов = " + amount;
    }

    public ArrayList<Route> printAscending() {
        Collections.sort(collection);
        return new ArrayList<>(collection);
    }

    public ArrayList<? extends Serializable> printDescending() {
        Collections.sort(collection, Collections.reverseOrder());
        return new ArrayList<>(collection);
    }


    public synchronized ArrayList<? extends Serializable> show() {
        ArrayList<Route> result = new ArrayList<>();
        for(Route element: collection) {
            result.add(element);
        }
        return result;
    }


    public boolean clear(String user) {
        for (Route element: collection) {
            int number = databaseManager.remove(element.getId(), user);
            if (number == -1) {
                return false;
            }
            else if (number > 0){
                collection.remove(element);
            }
        }
        return true;
    }


    public boolean add(Route element, String owner) {
        Long id = databaseManager.add(element, owner);
        if (id != null) {
            element.setId(id);
            element.setOwner(owner);
            collection.add(element);
            return true;
        }
        return false;
    }


    public String updateById(Long updateID, Route object, String user) {
        String result = "Команда выполнена успешно";
        int number = databaseManager.update(object, updateID, user);
        if (number == -1) {
            result = updateID + " - ошибка обновления. Попробуйте позже";
        }
        else if (number == 0) result = "Объекта нет в коллекции или у вас недостаточно прав для его изменения";
        else if (number > 0){
            object.setId(updateID);
            collection.removeIf(element -> element.getId() == updateID);
            collection.add(object);
        }
        return result;
    }


    public String removeById(Long deleteID, String user) {
        int number = databaseManager.remove(deleteID, user);
        String result = "Успешное удаление элемента с ID = " + deleteID;
        if (number == -1) {
            result = deleteID + " - ошибка удаления. Попробуйте позже";
        }
        else if (number == 0) {
            result = deleteID + " - элемент не был удален, т.к. нет прав или его нет в коллекции";
        }
        else {
            collection.removeIf(element -> element.getId() == deleteID);
        }
        return result;
    }

    public boolean collection_initialization() {
        ResultSet collectionFromDB = databaseManager.getFromDB();
        if (collectionFromDB == null) return false;
        try {
            while(collectionFromDB.next()) {
                try {
                    Route element = new Route(
                            collectionFromDB.getLong("id"),

                            collectionFromDB.getString("route_name"),

                            new Coordinates(collectionFromDB.getLong("route_x"),
                                    collectionFromDB.getInt("route_y")),

                            collectionFromDB.getDate("creation_date"),

                            new Location(collectionFromDB.getString("name_location_from"),
                                    collectionFromDB.getDouble("x_location_from"),
                                    collectionFromDB.getDouble("y_location_from")),

                            new Location(collectionFromDB.getString("name_location_to"),
                                    collectionFromDB.getDouble("x_location_to"),
                                    collectionFromDB.getDouble("y_location_to")),
                            collectionFromDB.getInt("distance"),
                            collectionFromDB.getString("user_name")
                    );

                    ValidateFromDatabase vfd = new ValidateFromDatabase();

                    if (vfd.validate(element)) collection.add(element);
                    else System.out.println("Значения элемента не подходят условию.");
                }
                catch (Exception e) {
                    System.out.println("Некорректный элемент в таблице. Проверьте таблицу!");
                }
            }
            return true;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}

