package database;


import data.Route;

import java.sql.*;
import java.time.Instant;

public class DatabaseManager {
    private Connection connection;
    public DatabaseManager() {
        connection = DatabaseConnection.getConnection();
    }

    public ResultSet getFromDB() {
        String sql = "select * from route";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet result = preparedStatement.executeQuery();
            return result;
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Long add(Route element, String owner) {
        try {
            String sql = "insert into route (route_name, route_x, route_y, creation_date, name_location_from, x_location_from, y_location_from, name_location_to, x_location_to, y_location_to, distance, user_name) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) returning id";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            Instant instant = element.getCreationDate().toInstant();
            Timestamp ts = instant != null ? Timestamp.from(instant) : null;
            preparedStatement.setString(1, element.getName());
            preparedStatement.setLong(2, element.getCoordinates().getX());
            preparedStatement.setInt(3, element.getCoordinates().getY());
            preparedStatement.setTimestamp(4, ts);
            preparedStatement.setString(5, element.getFrom().getName());
            preparedStatement.setDouble(6, element.getFrom().getX());
            preparedStatement.setDouble(7, element.getFrom().getY());
            preparedStatement.setString(8, element.getTo().getName());
            preparedStatement.setDouble(9, element.getTo().getX());
            preparedStatement.setDouble(10, element.getTo().getY());
            preparedStatement.setInt(11, element.getDistance());
            preparedStatement.setString(12, owner);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            return result.getLong("id");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Integer update(Route element, Long id, String user) {
        try {
            String sql = "update route set (route_name, route_x, route_y, creation_date, name_location_from, x_location_from, y_location_from, name_location_to, x_location_to, y_location_to, distance) = (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) where id = ? and user_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            Instant instant = element.getCreationDate().toInstant();
            Timestamp ts = instant != null ? Timestamp.from(instant) : null;
            preparedStatement.setString(1, element.getName());
            preparedStatement.setLong(2, element.getCoordinates().getX());
            preparedStatement.setInt(3, element.getCoordinates().getY());
            preparedStatement.setTimestamp(4, ts);
            preparedStatement.setString(5, element.getFrom().getName());
            preparedStatement.setDouble(6, element.getFrom().getX());
            preparedStatement.setDouble(7, element.getFrom().getY());
            preparedStatement.setString(8, element.getTo().getName());
            preparedStatement.setDouble(9, element.getTo().getX());
            preparedStatement.setDouble(10, element.getTo().getY());
            preparedStatement.setInt(11, element.getDistance());
            preparedStatement.setLong(12, id);
            preparedStatement.setString(13, user);
            return preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public Integer remove(Long id, String user) {
        try {
            String sql = "delete from route where id = ? and user_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, user);
            return preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }


}