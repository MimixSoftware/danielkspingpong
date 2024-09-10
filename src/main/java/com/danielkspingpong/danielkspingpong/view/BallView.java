package com.danielkspingpong.danielkspingpong.view;

import com.danielkspingpong.danielkspingpong.model.BallInterface;
import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;

/**
 * The view of the Ball.
 */
public class BallView implements Serializable
{
    /**
     * The interface for the ball.
     */
    private BallInterface ball;

    /**
     * The constructor.
     * @param ball The ball interface.
     */
    public BallView(BallInterface ball)
    {
        this.ball = ball;
    }

    /**
     * Prints information about the ball.
     */
    public void printBallDetails()
    {
        System.out.println("-");
        System.out.println("BALL DETAILS: ");
        System.out.println("radius: " + ball.getRadius());
        System.out.println("initialXSpeed: " + ball.getInitialXSpeed());
        System.out.println("initialYSpeed: " + ball.getInitialYSpeed());
        System.out.println("xSpeed: " + ball.getXSpeed());
        System.out.println("ySpeed: " + ball.getYSpeed());
        System.out.println("xPosition: " + ball.getXPosition());
        System.out.println("yPosition: " + ball.getYPosition());
        System.out.println("-");
    }

    /**
     * Renders the ball.
     * @param gc The graphics context.
     */
    public void render(GraphicsContext gc)
    {
        gc.fillOval(ball.getXPosition(), ball.getYPosition(), ball.getRadius(), ball.getRadius());
    }
}
