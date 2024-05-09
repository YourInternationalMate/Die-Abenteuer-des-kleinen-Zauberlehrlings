package data;

import java.sql.*;

public class SQLite {

    private static final String DATABASE_URL = "jdbc:sqlite:src/data/game.db";

    public static String getLevelNumber(String query) {
        String result = "";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {
                result = resultSet.getString("level");
            }
        } catch (SQLException e) {
            System.out.println("Fehler beim Verbinden zur Datenbank: " + e.getMessage());
        }
        return result;
    }

    public static void updateLevel(String query) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Fehler beim Verbinden zur Datenbank: " + e.getMessage());
        }
    }
}
