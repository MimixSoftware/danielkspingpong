package com.danielkspingpong.danielkspingpong;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The DatabaseConnection class is a singleton class responsible for connecting to the database and maintaining only one instance.
 */
public class DatabaseConnection
{
    /**
     * The URL of hte database.
     */
    private static final String URL = "jdbc:mysql://localhost:3306/game_database";
    /**
     * The username to access the database.
     */
    private static final String USER = "root";
    /**
     * The password to access the database.
     */
    private static final String PASSWORD = "password";
    /**
     * The connection instance.
     */
    private static Connection connection;

    /**
     * Private constructor to prevent instantiation.
     */
    private DatabaseConnection()
    {
        // Private constructor to prevent instantiation
    }

    /**
     * Function to retrieve the current connection, create it if it doesn't exist.
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
}