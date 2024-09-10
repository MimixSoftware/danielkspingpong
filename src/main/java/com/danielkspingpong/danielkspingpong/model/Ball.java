package com.danielkspingpong.danielkspingpong.model;

import java.util.Random;

/**
 * The ball.
 */
public class Ball implements BallInterface
{
    /**
     * The ball radius.
     */
    private double radius;
    /**
     * The initial ball x speed.
     */
    private int initialXSpeed;
    /**
     * The initial ball y speed.
     */
    private int initialYSpeed;
    /**
     * The ball x speed.
     */
    private int xSpeed = initialXSpeed;
    /**
     * The ball y speed.
     */
    private int ySpeed = initialYSpeed;
    /**
     * The ball x position.
     */
    private double xPosition;
    /**
     * The ball y position.
     */
    private double yPosition;

    /**
     * The constructor.
     * @param xPosition The x position.
     * @param yPosition The y position.
     * @param initialXSpeed The initial x speed.
     * @param initialYSpeed The initial y speed.
     */
    //Constructor
    public Ball(double xPosition, double yPosition, int initialXSpeed, int initialYSpeed)
    {
        this.radius = 15;
        this.initialXSpeed = initialXSpeed;
        this.initialYSpeed = initialYSpeed;
        this.xSpeed = initialXSpeed;
        this.ySpeed = initialYSpeed;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    //Getters
    /**
     * @return Returns the ball radius.
     */
    public double getRadius()
    {
        return radius;
    }
    /**
     * @return Returns the initial ball x speed.
     */
    public int getInitialXSpeed()
    {
        return initialXSpeed;
    }
    /**
     * @return Returns the initial ball y speed.
     */
    public int getInitialYSpeed()
    {
        return initialYSpeed;
    }
    /**
     * @return Returns the ball x speed.
     */
    public int getXSpeed()
    {
        return xSpeed;
    }
    /**
     * @return Returns the ball y speed.
     */
    public int getYSpeed()
    {
        return ySpeed;
    }
    /**
     * @return Returns the ball x position.
     */
    public double getXPosition()
    {
        return xPosition;
    }
    /**
     * @return Returns the ball y position.
     */
    public double getYPosition()
    {
        return yPosition;
    }

    //Setters
    /**
     * Sets the ball radius.
     * @param newRadius The radius.
     */
    public void setRadius(double newRadius)
    {
        radius = newRadius;
    }
    /**
     * Sets the initial ball x speed.
     * @param newInitialBallXSpeed The initial x speed.
     */
    public void setInitialXSpeed(int newInitialBallXSpeed)
    {
        initialXSpeed = newInitialBallXSpeed;
    }
    /**
     * Sets the initial ball y speed.
     * @param newInitialBallYSpeed The initial y speed.
     */
    public void setInitialYSpeed(int newInitialBallYSpeed)
    {
        initialYSpeed = newInitialBallYSpeed;
    }
    /**
     * Sets the ball x speed.
     * @param newBallXSpeed The x speed.
     */
    public void setXSpeed(int newBallXSpeed)
    {
        xSpeed = newBallXSpeed;
    }
    /**
     * Sets the ball y speed.
     * @param newBallYSpeed The y speed.
     */
    public void setYSpeed(int newBallYSpeed)
    {
        ySpeed = newBallYSpeed;
    }
    /**
     * Sets the ball x position.
     * @param newBallXPosition The x position.
     */
    public void setXPosition(double newBallXPosition)
    {
        xPosition = newBallXPosition;
    }
    /**
     * Sets the ball y position.
     * @param newBallYPosition The y position.
     */
    public void setYPosition(double newBallYPosition)
    {
        yPosition = newBallYPosition;
    }

    //Methods
    /**
     * Sets a random trajectory.
     */
    public void setRandomTrajectory()
    {
        xSpeed = new Random().nextInt(2) == 0 ? xSpeed: -xSpeed;
        ySpeed = new Random().nextInt(2) == 0 ? ySpeed: -ySpeed;
    }

    /**
     * Reverses the x speed.
     */
    public void reverseXSpeed()
    {
        xSpeed = -xSpeed;
    }
    /**
     * Reverses the y speed.
     */
    public void reverseYSpeed()
    {
        ySpeed = -ySpeed;
    }
    /**
     * Increments the x speed.
     */
    public void incrementXSpeed()
    {
        xSpeed += 1 * Math.signum(xSpeed);
    }
    /**
     * Increments the y speed.
     */
    public void incrementYSpeed()
    {
        ySpeed += 1 * Math.signum(ySpeed);
    }
    /**
     * Reset the x and y speeds to initial values.
     */
    public void resetSpeed()
    {
        xSpeed = initialXSpeed;
        ySpeed = initialYSpeed;
    }
    /**
     * Updates the ball position.
     */
    public void updatePosition()
    {
        xPosition += xSpeed;
        yPosition += ySpeed;
    }
}