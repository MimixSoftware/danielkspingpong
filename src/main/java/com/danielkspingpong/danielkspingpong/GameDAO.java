package com.danielkspingpong.danielkspingpong;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.TextInputDialog;

/**
 * The Data Access Object that manages all operations with the database.
 */
public class GameDAO
{
    /**
     * Function to save the current game to the database.
     * @param game The game we want to save
     */
    public static void saveGame(Game game)
    {
        String gameName = promptForGameName();
        if (gameName != null)
        {
            String sql = "INSERT INTO Game (game_name, player1_name, player2_name, player1_score, player2_score, game_limit) VALUES (?, ?, ?, ?, ?, ?)";
            Object[] gameData = game.getDataForDatabase();

            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql))
            {

                statement.setString(1, gameName);
                statement.setString(2, (String)gameData[0]);
                statement.setString(3, (String)gameData[1]);
                statement.setInt(4, (Integer)gameData[2]);
                statement.setInt(5, (Integer)gameData[3]);
                statement.setInt(6, (Integer)gameData[4]);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0)
                {
                    showAlert("Game information saved successfully.", true);
                }
            }
            catch (SQLException e)
            {
                showAlert("Error saving game information: " + e.getMessage(), false);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Function to load the current game from the database.
     * @param game The game instance which will be updated from the database.
     */
    public static void loadGame(Game game)
    {
        String sql = "SELECT * FROM Game ORDER BY id DESC LIMIT 1";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery())
        {

            if (resultSet.next())
            {
                String player1Name = resultSet.getString("player1_name");
                String player2Name = resultSet.getString("player2_name");
                int player1Score = resultSet.getInt("player1_score");
                int player2Score = resultSet.getInt("player2_score");
                int endScore = resultSet.getInt("game_limit");

                game.setDataFromDatabase(player1Name, player2Name, player1Score, player2Score, endScore);
            }
            else
            {
                showAlert("No game found in the database.", true);
            }
        }
        catch (SQLException e)
        {
            showAlert("Error loading game information: " + e.getMessage(), false);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Function to display alerts related to the game saving/loading process.
     */
    private static void showAlert(String message, Boolean successful)
    {
        Alert alert = new Alert(successful ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle("Game Save/Load");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Function to display prompt to get game name.
     */
    private static String promptForGameName()
    {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Game Name");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter the name of the game:");

        return dialog.showAndWait().orElse(null);
    }
}
