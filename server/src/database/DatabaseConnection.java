package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection databaseConnection;
    private static Connection connection;
    private String DB_USERNAME = "s367056";
    private String DB_PASSWORD = "hREvnwpB3e8wEhyp";
    private String DB_URL = "jdbc:postgresql://pg:5432/studs";

    private DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Ошибка подключения к базе данных. Попробуйте позже");
            System.exit(0);
        }
    }

    public static Connection getConnection() {
        if (databaseConnection == null) {
            databaseConnection = new DatabaseConnection();
        }
        return connection;
    }
}