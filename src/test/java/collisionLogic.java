import com.danielkspingpong.danielkspingpong.model.BallInterface;
import com.danielkspingpong.danielkspingpong.model.PlayerInterface;
import com.danielkspingpong.danielkspingpong.model.Ball;
import com.danielkspingpong.danielkspingpong.model.Player;

/**
 * Contains collision logic for testing purposes.
 */
public class collisionLogic
{
    /**
     * The width of the stage.
     */
    public static int width = 800;
    /**
     * The height of the stage.
     */
    public static int height = 600;
    /**
     * The width of the players.
     */
    private static int playerWidth = 15;
    /**
     * The height of the players.
     */
    private static int playerHeight = 100;
    /**
     * The interface for a dummy ball.
     */
    public BallInterface ball = new Ball(width / 2.0, height / 2.0, 1, 1);
    /**
     * The interface for a dummy player 1.
     */
    public PlayerInterface player1 = new Player("Player 1", 60, height / 2.0, 0);
    /**
     * The interface for a dummy player 2.
     */
    public PlayerInterface player2 = new Player("Player 2", width - 60 - playerWidth, height / 2.0, 0);

    /**
     * Checks if the ball has collided with either paddle.
     * @return Returns if a collision occured or not.
     */
    public boolean checkForPaddleCollision()
    {
        if (((ball.getXPosition() + ball.getRadius() > player2.getXPosition()) && ball.getYPosition() >= player2.getYPosition() && ball.getYPosition() <= player2.getYPosition() + playerHeight) ||
                (ball.getXPosition() < player1.getXPosition() + playerWidth) && ball.getYPosition() >= player1.getYPosition() && ball.getYPosition() <= player1.getYPosition() + playerHeight)
        {
            ball.reverseXSpeed();
            ball.reverseYSpeed();
            return true;
        }
        return false;
    }

    /**
     * Checks if the ball has collided with an edge.
     * @return Returns if a collision has occured or not.
     */
    public boolean checkForEdgeCollision()
    {
        if (ball.getYPosition() > height ||  ball.getYPosition() < 0)
        {
            ball.reverseYSpeed();
            return true;
        }
        return false;
    }
}
