package database;

import utils.PasswordHash;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserExist {
    private static Connection connection = DatabaseConnection.getConnection();

    private UserExist() {
    }

    public static String register(String user, String password) {
        String sql = "select * from user_info where login = ?";
        try {
            System.out.println("in database");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) return "Пользователь с таким логином уже существует";

            sql = "insert into user_info (login, password) values (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, PasswordHash.getHash(password));
            preparedStatement.executeUpdate();
            return "Успешно";
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return "Ошибка подключения к БД. Повторите запрос позднее";
        }
    }

    public static String login(String user, String password) {
        String sql = "select * from user_info where login = ? and password = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, PasswordHash.getHash(password));
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) return "Успешно";
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return "Ошибка подключения к БД. Повторите запрос позднее";
        }
        return "Неверный логин или пароль";
    }

}