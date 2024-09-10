package com.danielkspingpong.danielkspingpong.view;

import com.danielkspingpong.danielkspingpong.model.PlayerInterface;
import com.danielkspingpong.danielkspingpong.model.SettingsInterface;
import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;

/**
 * The view of the Player.
 */
public class PlayerView implements Serializable
{
    /**
     * The interface for the player.
     */
    private PlayerInterface player;

    /**
     * The constructor.
     * @param player The player interface.
     */
    public PlayerView(PlayerInterface player)
    {
        this.player = player;
    }

    /**
     * Prints information about the player.
     */
    public void printPlayerDetails()
    {
        System.out.println("-");
        System.out.println("PLAYER DETAILS: ");
        System.out.println("name: " + player.getName());
        System.out.println("xPosition: " + player.getXPosition());
        System.out.println("yPosition: " + player.getYPosition());
        System.out.println("speed: " + player.getSpeed());
        System.out.println("score: " + player.getScore());
        System.out.println("-");
    }

    /**
     * Renders the player.
     * @param gc The graphics context.
     * @param settings The settings interface.
     */
    public void render(GraphicsContext gc, SettingsInterface settings)
    {
        gc.fillRect(player.getXPosition(), player.getYPosition(), settings.getPlayerWidth(), settings.getPlayerHeight());
    }
}