package com.danielkspingpong.danielkspingpong.model;

import java.io.*;
import java.util.Properties;

/**
 * The settings.
 */
public class Settings implements SettingsInterface
{
    /**
     * The player height.
     */
    private int playerHeight;
    /**
     * The player width.
     */
    private int playerWidth;
    /**
     * The initial ball x speed.
     */
    private int initialBallXSpeed;
    /**
     * The initial ball y speed.
     */
    private int initialBallYSpeed;
    /**
     * The endscore.
     */
    private int endScore;
    /**
     * The ball speed increase setting.
     */
    private int ballSpeedIncreaseSetting;
    /**
     * Whether player 2 is computer controlled.
     */
    private boolean player2ComputerControlled;
    /**
     * The player 1 default name.
     */
    private String player1DefaultName;
    /**
     * The player 2 default name.
     */
    private String player2DefaultName;

    /**
     * The constructor.
     */
    //Constructor
    public Settings()
    {
        this.playerHeight = 100;
        this.playerWidth = 15;
        this.initialBallXSpeed = 1;
        this.initialBallYSpeed = 1;
        this.endScore = 3;
        this.ballSpeedIncreaseSetting = 5;
        this.player2ComputerControlled = false;
        this.player1DefaultName = "Player 1";
        this.player2DefaultName = "Player 2";
    }

    //Getters
    /**
     * @return Returns the player height.
     */
    public int getPlayerHeight()
    {
        return playerHeight;
    }
    /**
     * @return Returns the player width.
     */
    public int getPlayerWidth()
    {
        return playerWidth;
    }
    /**
     * @return Returns the initial ball x speed.
     */
    public int getInitialBallXSpeed()
    {
        return initialBallXSpeed;
    }
    /**
     * @return Returns the initial ball y speed.
     */
    public int getInitialBallYSpeed()
    {
        return initialBallYSpeed;
    }
    /**
     * @return Returns the end score.
     */
    public int getEndScore()
    {
        return endScore;
    }
    /**
     * @return Returns the number of bounces after which the ball speed is incremented.
     */
    public int getBallSpeedIncreaseSetting()
    {
        return ballSpeedIncreaseSetting;
    }
    /**
     * @return Returns whether player 2 is computer controlled.
     */
    public boolean getPlayer2ComputerControlled()
    {
        return player2ComputerControlled;
    }
    /**
     * @return Returns the player 1 default name.
     */
    public String getPlayer1DefaultName()
    {
        return player1DefaultName;
    }
    /**
     * @return Returns the player 2 default name.
     */
    public String getPlayer2DefaultName()
    {
        return player2DefaultName;
    }

    //Setters
    /**
     * Sets the player height.
     * @param newPlayerHeight The player height.
     */
    public void setPlayerHeight(int newPlayerHeight)
    {
        playerHeight = newPlayerHeight;
    }
    /**
     * Sets the player width.
     * @param newPlayerWidth The player width.
     */
    public void setPlayerWidth(int newPlayerWidth)
    {
        playerWidth = newPlayerWidth;
    }
    /**
     * Sets the initial ball x speed.
     * @param newInitialBallXSpeed The initial ball x speed.
     */
    public void setInitialBallXSpeed(int newInitialBallXSpeed)
    {
        initialBallXSpeed = newInitialBallXSpeed;
    }
    /**
     * Sets the initial ball y speed.
     * @param newInitialBallYSpeed The initial ball y speed.
     */
    public void setInitialBallYSpeed(int newInitialBallYSpeed)
    {
        initialBallYSpeed = newInitialBallYSpeed;
    }
    /**
     * Sets the end score.
     * @param newEndScore The end score.
     */
    public void setEndScore(int newEndScore)
    {
        endScore = newEndScore;
    }
    /**
     * Sets the ball speed increase setting.
     * @param newBallSpeedIncreaseSetting The amount of bounces after which ball speed is incremented.
     */
    public void setBallSpeedIncreaseSetting(int newBallSpeedIncreaseSetting)
    {
        ballSpeedIncreaseSetting = newBallSpeedIncreaseSetting;
    }
    /**
     * Sets whether player 2 is computer controlled.
     * @param newPlayer2ComputerControlled Whether player 2 is computer controlled.
     */
    public void setPlayer2ComputerControlled(boolean newPlayer2ComputerControlled)
    {
        player2ComputerControlled = newPlayer2ComputerControlled;
    }
    /**
     * Sets the default name of player 1.
     * @param newPlayer1DefaultName The default name of player 1.
     */
    public void setPlayer1DefaultName(String newPlayer1DefaultName)
    {
        player1DefaultName = newPlayer1DefaultName;
    }
    /**
     * Sets the default name of player 2.
     * @param newPlayer2DefaultName The default name of player 2.
     */
    public void setPlayer2DefaultName(String newPlayer2DefaultName)
    {
        player2DefaultName = newPlayer2DefaultName;
    }

    //Method to save settings to a properties file
    /**
     * Saves settings to a serialised file.
     * @param fileName The destination file.
     */
    public void saveSettings(String fileName)
    {
        try (OutputStream output = new FileOutputStream(fileName))
        {
            Properties prop = new Properties();
            prop.setProperty("playerHeight", String.valueOf(playerHeight));
            prop.setProperty("playerWidth", String.valueOf(playerWidth));
            prop.setProperty("initialBallXSpeed", String.valueOf(initialBallXSpeed));
            prop.setProperty("initialBallYSpeed", String.valueOf(initialBallYSpeed));
            prop.setProperty("endScore", String.valueOf(endScore));
            prop.setProperty("ballSpeedIncreaseSetting", String.valueOf(ballSpeedIncreaseSetting));
            prop.setProperty("player2ComputerControlled", String.valueOf(player2ComputerControlled));
            prop.setProperty("player1DefaultName", String.valueOf(player1DefaultName));
            prop.setProperty("player2DefaultName", String.valueOf(player2DefaultName));

            prop.store(output, null);
            System.out.println("NOTE - Settings saved successfully.");
        }
        catch (IOException e)
        {
            System.err.println("ERROR - Could not save settings. (" + e.getMessage() + ")");
        }
    }

    //Method to load settings from a properties file
    /**
     * Loads settings from a serialised file.
     * @param fileName The target file.
     * @return Returns the loaded settings file or default if loading fails..
     */
    public Settings loadSettings(String fileName)
    {
        Settings settings = new Settings();
        try (InputStream input = new FileInputStream(fileName))
        {
            Properties prop = new Properties();
            prop.load(input);

            // Load properties
            settings.playerHeight = Integer.parseInt(prop.getProperty("playerHeight"));
            settings.playerWidth = Integer.parseInt(prop.getProperty("playerWidth"));
            settings.initialBallXSpeed = Integer.parseInt(prop.getProperty("initialBallXSpeed"));
            settings.initialBallYSpeed = Integer.parseInt(prop.getProperty("initialBallYSpeed"));
            settings.endScore = Integer.parseInt(prop.getProperty("endScore"));
            settings.ballSpeedIncreaseSetting = Integer.parseInt(prop.getProperty("ballSpeedIncreaseSetting"));
            settings.player2ComputerControlled = Boolean.parseBoolean(prop.getProperty("player2ComputerControlled"));
            settings.player1DefaultName = prop.getProperty("player1DefaultName");
            settings.player2DefaultName = prop.getProperty("player2DefaultName");

            System.out.println("NOTE - Settings loaded successfully.");
        }
        catch (IOException e)
        {
            System.err.println("ERROR -  Could not load settings, resetting to defaults. (" + e.getMessage() + ")");
        }
        return settings;
    }
}
