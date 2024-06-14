package data;

import java.sql.*;

public class SQLite {

    // URL der SQLite-Datenbank
    private static final String DATABASE_URL = "jdbc:sqlite:src/data/game.db";

    // Methode zum Abrufen der Levelnummer aus der Datenbank
    public static String getLevelNumber(String query) {
        String result = "";

        // Verbindung zur Datenbank herstellen und Abfrage ausführen
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {
                result = resultSet.getString("level"); // Levelnummer aus dem ResultSet extrahieren
            }
        } catch (SQLException e) {
            System.out.println("Fehler beim Verbinden zur Datenbank: " + e.getMessage());
        }
        return result;
    }

    // Methode zum Aktualisieren des Levels in der Datenbank
    public static void updateLevel(String query) {
        // Verbindung zur Datenbank herstellen und Abfrage ausführen
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(query); // Abfrage ausführen
        } catch (SQLException e) {
            System.out.println("Fehler beim Verbinden zur Datenbank: " + e.getMessage());
        }
    }

    // Methode zum Einfügen eines neuen Levels in die Datenbank
    public static void insertLevel(String query) {
        // Verbindung zur Datenbank herstellen und Abfrage ausführen
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(query); // Abfrage ausführen
        } catch (SQLException e) {
            System.out.println("Fehler beim Verbinden zur Datenbank: " + e.getMessage());
        }
    }
}