package com.danielkspingpong.danielkspingpong.model;

import java.io.Serializable;

/**
 * The interface for settings.
 */
public interface SettingsInterface extends Serializable
{
        /**
         * @return Returns the player height.
         */
        int getPlayerHeight();

        /**
         * @return Returns the player width.
         */
        int getPlayerWidth();

        /**
         * @return Returns the initial ball x speed.
         */
        int getInitialBallXSpeed();

        /**
         * @return Returns the initial ball y speed.
         */
        int getInitialBallYSpeed();

        /**
         * @return Returns the end score.
         */
        int getEndScore();

        /**
         * @return Returns the number of bounces after which the ball speed is incremented.
         */
        int getBallSpeedIncreaseSetting();

        /**
         * @return Returns whether player 2 is computer controlled.
         */
        boolean getPlayer2ComputerControlled();

        /**
         * @return Returns the player 1 default name.
         */
        String getPlayer1DefaultName();

        /**
         * @return Returns the player 2 default name.
         */
        String getPlayer2DefaultName();

        /**
         * Sets the player height.
         * @param playerHeight The player height.
         */
        void setPlayerHeight(int playerHeight);

        /**
         * Sets the player width.
         * @param playerWidth The player width.
         */
        void setPlayerWidth(int playerWidth);

        /**
         * Sets the initial ball x speed.
         * @param initialBallXSpeed The initial ball x speed.
         */
        void setInitialBallXSpeed(int initialBallXSpeed);

        /**
         * Sets the initial ball y speed.
         * @param initialBallYSpeed The initial ball y speed.
         */
        void setInitialBallYSpeed(int initialBallYSpeed);

        /**
         * Sets the end score.
         * @param newEndScore The end score.
         */
        void setEndScore(int newEndScore);

        /**
         * Sets the ball speed increase setting.
         * @param newBallSpeedIncreaseSetting The amount of bounces after which ball speed is incremented.
         */
        void setBallSpeedIncreaseSetting(int newBallSpeedIncreaseSetting);

        /**
         * Sets whether player 2 is computer controlled.
         * @param newPlayer2ComputerControlled Whether player 2 is computer controlled.
         */
        void setPlayer2ComputerControlled(boolean newPlayer2ComputerControlled);

        /**
         * Sets the default name of player 1.
         * @param newPlayer1DefaultName The default name of player 1.
         */
        void setPlayer1DefaultName(String newPlayer1DefaultName);

        /**
         * Sets the default name of player 2.
         * @param newPlayer2DefaultName The default name of player 2.
         */
        void setPlayer2DefaultName(String newPlayer2DefaultName);

        /**
         * Saves settings to a serialised file.
         * @param fileName The destination file.
         */
        void saveSettings(String fileName);

        /**
         * Loads settings from a serialised file.
         * @param fileName The target file.
         * @return Returns the loaded settings file or default if loading fails..
         */
        Settings loadSettings(String fileName);
}
