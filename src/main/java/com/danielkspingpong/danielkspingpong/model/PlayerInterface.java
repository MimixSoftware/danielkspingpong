package com.danielkspingpong.danielkspingpong.model;

import java.io.Serializable;

/**
 * The interface for the player.
 */
public interface PlayerInterface extends Serializable
{
    /**
     * @return Returns the player name.
     */
    String getName();

    /**
     * @return Returns the player x position.
     */
    double getXPosition();

    /**
     * @return Returns the player y position.
     */
    double getYPosition();

    /**
     * @return Returns the player score.
     */
    int getScore();

    /**
     * @return Returns the player speed.
     */
    int getSpeed();

    /**
     * Sets the player name.
     * @param name The name.
     */
    void setName(String name);

    /**
     * Sets the player x position.
     * @param xPosition The x position.
     */
    void setXPosition(double xPosition);

    /**
     * Sets the player y position.
     * @param yPosition The y position.
     * @return Returns the new y position.
     */
    double setYPosition(double yPosition);

    /**
     * Sets the player score.
     * @param score The score.
     */
    void setScore(int score);

    /**
     * Sets the player speed.
     * @param newSpeed The speed.
     */
    void setSpeed(int newSpeed);

    /**
     * Increments the score by 1.
     * @return Returns the new score.
     */
    int score();

    /**
     * Updates the player position.
     */
    void updatePosition();
}
