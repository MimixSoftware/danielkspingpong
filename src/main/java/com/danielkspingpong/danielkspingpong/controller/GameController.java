package com.danielkspingpong.danielkspingpong.controller;

import com.danielkspingpong.danielkspingpong.model.SettingsInterface;
import com.danielkspingpong.danielkspingpong.model.BallInterface;
import com.danielkspingpong.danielkspingpong.model.PlayerInterface;
import com.danielkspingpong.danielkspingpong.view.BallView;
import com.danielkspingpong.danielkspingpong.view.PlayerView;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;

/**
 * The game logic controller.
 */
public class GameController implements Serializable
{
    /**
     * The interface for settings.
     */
    private SettingsInterface settings;
    /**
     * The interface for the ball.
     */
    private BallInterface ball;
    /**
     * The interface for player 1.
     */
    private PlayerInterface player1;
    /**
     * The interface for player 2.
     */
    private PlayerInterface player2;
    /**
     * The interface for the ball view.
     */
    private BallView ballView;
    /**
     * The interface for the player 1 view.
     */
    private PlayerView player1View;
    /**
     * The interface for the player 2 view.
     */
    private PlayerView player2View;

    /**
     * The constructor for the game logic controller.
     * @param settings The game interface.
     * @param ball The  ball interface.
     * @param player1 The player 1 interface.
     * @param player2 The player 2 interface.
     */
    public GameController(SettingsInterface settings, BallInterface ball, PlayerInterface player1, PlayerInterface player2)
    {
        this.settings = settings;
        this.ball = ball;
        this.player1 = player1;
        this.player2 = player2;
        this.ballView = new BallView(ball);
        this.player1View = new PlayerView(player1);
        this.player2View = new PlayerView(player2);
    }

    /**
     * Renders both players via their view.
     * @param gc The graphics context.
     */
    public void renderPlayers(GraphicsContext gc)
    {
        player1View.render(gc, settings);
        player2View.render(gc, settings);
    }

    /**
     * Renders the ball via its view.
     * @param gc The graphics context.
     */
    public void renderBall(GraphicsContext gc)
    {
        ballView.render(gc);
    }

    /**
     * Calls the update functions of the relevant game entities to refresh their position, handles possible out-of-bounds scenarios.
     * @param height The height of the stage.
     */
    public void updatePlayerAndBallPositions(int height)
    {
        ball.updatePosition();
        player1.updatePosition();
        player2.updatePosition();

        if (player1.getYPosition() < 0)
        {
            player1.setYPosition(0);
        }
        else if (player1.getYPosition() > height)
        {
            player1.setYPosition(height);
        }

        if (player2.getYPosition() < 0)
        {
            player2.setYPosition(0);
        }
        else if (player2.getYPosition() > height)
        {
            player2.setYPosition(height);
        }
    }

    /**
     * The computer algorithm used by player 2.
     * @param width The stage width.
     */
    public void computerControlPlayer2(int width)
    {
        if (ball.getXPosition() < width - width / 4.0)
        {
            player2.setYPosition(ball.getYPosition() - settings.getPlayerHeight() / 2.0);
        }
        else
        {
            player2.setYPosition(ball.getYPosition() > player2.getYPosition() + settings.getPlayerHeight() / 2.0 ?player2.setYPosition(player2.getYPosition() + 1): player2.getYPosition() - 1);
        }
    }

    /**
     * Adds event listeners for player controls.
     * @param scene The scene.
     */
    public void listenForControls(Scene scene)
    {
        scene.setOnKeyPressed(event ->
        {
            switch (event.getCode())
            {
                case W:
                    player1.setSpeed(-5);
                    break;
                case S:
                    player1.setSpeed(5);
                    break;
                case UP:
                    if (!settings.getPlayer2ComputerControlled())
                        player2.setSpeed(-5);
                    break;
                case DOWN:
                    if (!settings.getPlayer2ComputerControlled())
                        player2.setSpeed(5);
                    break;
            }
        });

        scene.setOnKeyReleased(event ->
        {
            switch (event.getCode())
            {
                case W:
                case S:
                    player1.setSpeed(0);
                    break;
                case UP:
                case DOWN:
                    if (!settings.getPlayer2ComputerControlled())
                        player2.setSpeed(0);
                    break;
            }
        });
    }

    /**
     * Checks if the ball collides with a paddle.
     * @return Returns whether a collision occured or not.
     */
    public boolean checkForPaddleCollision()
    {
        if (((ball.getXPosition() + ball.getRadius() > player2.getXPosition()) && ball.getYPosition() >= player2.getYPosition() && ball.getYPosition() <= player2.getYPosition() + settings.getPlayerHeight()) ||
                (ball.getXPosition() < player1.getXPosition() + settings.getPlayerWidth()) && ball.getYPosition() >= player1.getYPosition() && ball.getYPosition() <= player1.getYPosition() + settings.getPlayerHeight())
        {
            ball.reverseXSpeed();
            ball.reverseYSpeed();
            return true;
        }
        return false;
    }

    /**
     * Checks if the ball collides with an edge.
     * @param height The height of the stage.
     * @return Returns whether a collision occured or not.
     */
    public boolean checkForEdgeCollision(int height)
    {
        if (ball.getYPosition() > height ||  ball.getYPosition() < 0)
        {
            ball.reverseYSpeed();
            return true;
        }
        return false;
    }
}
