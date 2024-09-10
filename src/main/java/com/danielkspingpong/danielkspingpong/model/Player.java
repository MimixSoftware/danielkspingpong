package com.danielkspingpong.danielkspingpong.model;

/**
 * The player.
 */
public class Player implements PlayerInterface
{
    /**
     * The player name.
     */
    private String name;
    /**
     * The player x position.
     */
    private double xPosition;
    /**
     * The player y position.
     */
    private double yPosition;
    /**
     * The player speed.
     */
    private int speed = 0;
    /**
     * The player score.
     */
    private int score;

    /**
     * The constructor.
     * @param name The name.
     * @param xPosition The x position.
     * @param yPosition The y position.
     * @param score The score.
     */
    //Constructor
    public Player(String name, double xPosition, double yPosition, int score) {
        this.name = name;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.score = score;
    }

    //Getters
    /**
     * @return Returns the player name.
     */
    public String getName()
    {
        return name;
    }
    /**
     * @return Returns the player x position.
     */
    public double getXPosition()
    {
        return xPosition;
    }
    /**
     * @return Returns the player y position.
     */
    public double getYPosition()
    {
        return yPosition;
    }
    /**
     * @return Returns the player score.
     */
    public int getScore()
    {
        return score;
    }
    /**
     * @return Returns the player speed.
     */
    public int getSpeed() { return speed; }

    //Setters
    /**
     * Sets the player name.
     * @param newName The name.
     */
    public void setName(String newName)
    {
        name = newName;
    }
    /**
     * Sets the player x position.
     * @param newXPosition The x position.
     */
    public void setXPosition(double newXPosition)
    {
        xPosition = newXPosition;
    }
    /**
     * Sets the player y position.
     * @param newYPosition The y position.
     * @return Returns the new y position.
     */
    public double setYPosition(double newYPosition)
    {
        yPosition = newYPosition;
        return yPosition;
    }
    /**
     * Sets the player score.
     * @param newScore The score.
     */
    public void setScore(int newScore)
    {
        score = newScore;
    }
    /**
     * Sets the player speed.
     * @param newSpeed The speed.
     */
    public void setSpeed(int newSpeed)
    {
        speed = newSpeed;
    }

    //Methods
    /**
     * Increments the score by 1.
     * @return Returns the new score.
     */
    public int score()
    {
        return ++score;
    }
    /**
     * Updates the player position.
     */
    public void updatePosition()
    {
        yPosition += speed;
    }
}