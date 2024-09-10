package com.danielkspingpong.danielkspingpong.model;

import java.io.Serializable;

/**
 * The interface for the ball.
 */
public interface BallInterface extends Serializable
{
    /**
     * @return Returns the ball radius.
     */
    double getRadius();

    /**
     * @return Returns the initial ball x speed.
     */
    int getInitialXSpeed();

    /**
     * @return Returns the initial ball y speed.
     */
    int getInitialYSpeed();

    /**
     * @return Returns the ball x speed.
     */
    int getXSpeed();

    /**
     * @return Returns the ball y speed.
     */
    int getYSpeed();

    /**
     * @return Returns the ball x position.
     */
    double getXPosition();

    /**
     * @return Returns the ball y position.
     */
    double getYPosition();

    /**
     * Sets the ball radius.
     * @param radius The radius.
     */
    void setRadius(double radius);

    /**
     * Sets the initial ball x speed.
     * @param initialXSpeed The initial x speed.
     */
    void setInitialXSpeed(int initialXSpeed);

    /**
     * Sets the initial ball y speed.
     * @param initialYSpeed The initial y speed.
     */
    void setInitialYSpeed(int initialYSpeed);

    /**
     * Sets the ball x speed.
     * @param xSpeed The x speed.
     */
    void setXSpeed(int xSpeed);

    /**
     * Sets the ball y speed.
     * @param ySpeed The y speed.
     */
    void setYSpeed(int ySpeed);

    /**
     * Sets the ball x position.
     * @param xPosition The x position.
     */
    void setXPosition(double xPosition);

    /**
     * Sets the ball y position.
     * @param yPosition The y position.
     */
    void setYPosition(double yPosition);

    /**
     * Sets a random trajectory.
     */
    void setRandomTrajectory();

    /**
     * Reverses the x speed.
     */
    void reverseXSpeed();

    /**
     * Reverses the y speed.
     */
    void reverseYSpeed();

    /**
     * Increments the x speed.
     */
    void incrementXSpeed();

    /**
     * Increments the y speed.
     */
    void incrementYSpeed();

    /**
     * Reset the x and y speeds to initial values.
     */
    void resetSpeed();

    /**
     * Updates the ball position.
     */
    void updatePosition();
}
