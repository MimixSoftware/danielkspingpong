import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tester for collision logic.
 */
public class collisionLogicTest
{
    private final collisionLogic logic = new collisionLogic();

    /**
     * Tests if a collision between the ball and player 1 is detected.
     */
    @Test
    void ballHitsPlayer1()
    {
        logic.ball.setXPosition(logic.player1.getXPosition());
        boolean result = logic.checkForPaddleCollision();
        assertEquals(true, result);
    }

    /**
     * Tests if a collision between the ball and player 2 is detected.
     */
    @Test
    void ballHitsPlayer2()
    {
        logic.ball.setXPosition(logic.player2.getXPosition());
        boolean result = logic.checkForPaddleCollision();
        assertEquals(true, result);
    }

    /**
     * Tests if the lack of a collision between the ball and player 1 is correctly detected.
     */
    @Test
    void ballDoesNotHitPlayer1()
    {
        logic.ball.setXPosition(logic.player1.getXPosition());
        logic.ball.setYPosition(10);
        boolean result = logic.checkForPaddleCollision();
        assertEquals(false, result);
    }

    /**
     * Tests if the lack of a collision between the ball and player 2 is correctly detected.
     */
    @Test
    void ballDoesNotHitPlayer2()
    {
        logic.ball.setXPosition(logic.player2.getXPosition());
        logic.ball.setYPosition(10);
        boolean result = logic.checkForPaddleCollision();
        assertEquals(false, result);
    }

    /**
     * Tests if a collision between the ball and top edge is detected.
     */
    @Test
    void ballHitsTopEdge()
    {
        logic.ball.setYPosition(-1);
        boolean result = logic.checkForEdgeCollision();
        assertEquals(true, result);
    }

    /**
     * Tests if a collision between the ball and bottom edge is detected.
     */
    @Test
    void ballHitsBottomEdge()
    {
        logic.ball.setYPosition(collisionLogic.height + 1);
        boolean result = logic.checkForEdgeCollision();
        assertEquals(true, result);
    }

    /**
     * Tests if the lack of a collision with an edge is correctly detected.
     */
    @Test
    void ballDoesNotHitEdge()
    {
        boolean result = logic.checkForEdgeCollision();
        assertEquals(false, result);
    }
}
