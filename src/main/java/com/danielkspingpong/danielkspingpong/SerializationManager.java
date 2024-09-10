package com.danielkspingpong.danielkspingpong;

import javafx.scene.control.Alert;
import java.io.*;

/**
 * Singleton class to ensure only a single instance of serialization.
 */
public class SerializationManager
{
    /**
     * The current instance of SerializationManager.
     */
    private static SerializationManager instance;
    /**
     * The name of the save file.
     */
    private static final String SAVE_FILENAME = "save.ser";

    /**
     * A private constructor to prevent instantiation.
     */
    private SerializationManager()
    {
        // Private constructor to prevent instantiation
    }

    /**
     * Gets the current instance, creates it if it is null.
     */
    public static SerializationManager getInstance()
    {
        if (instance == null)
        {
            instance = new SerializationManager();
        }
        return instance;
    }

    /**
     * Function to serialise the parameter game to a save file.
     * @param game The Game to be serialised.
     */
    public void saveGame(Game game)
    {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SAVE_FILENAME)))
        {
            out.writeObject(game);
            showAlert("Game saved successfully!", true);
        }
        catch (IOException e)
        {
            showAlert("Error saving game: " + e.getMessage(), false);
        }
    }

    /**
     * Function to deserialize the Game from the save file.
     */
    public Game loadGame()
    {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(SAVE_FILENAME)))
        {
            Game loadedGame = (Game) in.readObject();
            showAlert("Game loaded successfully!", true);
            return loadedGame;
        }
        catch (IOException | ClassNotFoundException e)
        {
            showAlert("Error loading game state: " + e.getMessage(), false);
            return null;
        }
    }

    /**
     * Function to display alerts related to the game serialization process.
     */
    private void showAlert(String message, Boolean successful)
    {
        Alert alert = new Alert(successful ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle("Game Save/Load");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
